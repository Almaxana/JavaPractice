package ru.itmo.Core.Services;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Accounts.Account;
import ru.itmo.Core.Model.Accounts.Credit;
import ru.itmo.Core.Model.Accounts.Debet;
import ru.itmo.Core.Model.Accounts.Deposit;
import ru.itmo.Core.Model.Bank;
import ru.itmo.Core.Model.Customer;
import ru.itmo.Core.Model.Transactions.AddMoneyTransaction;
import ru.itmo.Core.Model.Transactions.GetMoneyTransaction;
import ru.itmo.Core.Model.Transactions.MoveMoneyTransaction;
import ru.itmo.Core.Repositories.IAccountRepository;
import ru.itmo.Core.Repositories.IBankRepository;
import ru.itmo.Core.Repositories.ITransactionRepository;
import ru.itmo.DataAccess.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Service making operations with bank accounts
 */
@AllArgsConstructor
public class AccountService {
    private IAccountRepository accountRepository;
    private IBankRepository bankRepository;
    private CurrentStateManager currentStateManager;
    private ITransactionRepository transactionRepository;

    /**
     * creates a debet account
     * @param accountId
     * @param currentSum
     * @param bankName
     * @return success or failure with message
     */
    public OperationResult createDebetAccount(UUID accountId, BigDecimal currentSum, String bankName) throws AppException {
        Bank bank = bankRepository.getBankByName(bankName);
        if (bank == null) throw  new AppException( "no such bank");
        return accountRepository.addAccount(Debet.builder()
                .bankId(bank.getBankId())
                .accountId(accountId)
                .customerId(currentStateManager.getCustomerInSystem().getId())
                .currentSum(currentSum)
                .dateOfStart(currentStateManager.getCurrentData())
                .fixPercent(bank.getFixedDebetPercent())
                .build());
    }

    /**
     * creates a creadit account
     * @param accountId
     * @param currentSum
     * @param bankName
     * @return success or failure with message
     */
    public OperationResult createCreditAccount(UUID accountId, BigDecimal currentSum, String bankName) throws AppException {
        Bank bank = bankRepository.getBankByName(bankName);
        if (bank == null) throw  new AppException( "no such bank");;
        return accountRepository.addAccount(Credit.builder()
                .accountId(accountId)
                .currentSum(currentSum)
                .maxNegativeSum(bank.getCreditMaxNegativeSum())
                .customerId(currentStateManager.getCustomerInSystem().getId())
                .bankId(bank.getBankId())
                .commission( bank.getCommission())
                .build());
    }

    /**
     * creates a credit account
     * @param accountId
     * @param currentSum in rubles
     * @param bankName
     * @param length of credit period in days
     * @return success or failure with message
     */
    public OperationResult createDepositAccount(UUID accountId, BigDecimal currentSum, String bankName, int length) throws AppException {
        Bank bank = bankRepository.getBankByName(bankName);
        if (bank == null) throw  new AppException( "no such bank");
        return accountRepository.addAccount(Deposit.builder()
                .accountId(accountId)
                .currentSum(currentSum)
                .length(length)
                .customerId(currentStateManager.getCustomerInSystem().getId())
                .bankId(bank.getBankId())
                .percent(bank.countDepositPercent(currentSum))
                .build());
    }

    /**
     * finds accounts of the given customer
     * @param customerId
     * @return accounts
     */
    public List<Account> getAccountsByCustomerId(UUID customerId){
        return accountRepository.getAccountsByCustomerId(customerId);
    }

    /**
     * adds money to the account
     * @param accountId
     * @param sum of added money
     */
    public void addMoney(UUID accountId, BigDecimal sum) throws AppException {
        Account accountToOperate = accountRepository.getAccountById(accountId);
        AddMoneyTransaction transaction = AddMoneyTransaction.builder().account(accountToOperate).sum(sum).build();
        transactionRepository.addTransaction(transaction);
        try{
            transaction.execute();
        } catch (AppException exception){
            throw exception;
        }

    }

    /**
     * takes money from the given account
     * @param accountId
     * @param customer
     * @param sum
     * @return success or failure with message
     */
    public OperationResult GetMoney(UUID accountId, Customer customer, BigDecimal sum) throws AppException {
        Account accountToOperate = accountRepository.getAccountById(accountId);
        Bank bank = bankRepository.getBankById(accountToOperate.getBankId());
        GetMoneyTransaction transaction = GetMoneyTransaction.builder()
                .account(accountToOperate)
                .customer(customer)
                .sum(sum)
                .maxDoubtSum(bank.getMaxDoubtSum())
                .currentStateManager(currentStateManager)
                .build();
        transactionRepository.addTransaction(transaction);
        try{
            return transaction.execute();
        } catch (AppException exception){
            throw exception;
        }

    }

    /**
     * moves money from one account to another
     * @param accountFromId
     * @param stringIdAccountTo
     * @param customer
     * @param sum of money
     * @return success or failure with message
     */
    public  OperationResult moveMoney(UUID accountFromId, String stringIdAccountTo, Customer customer, BigDecimal sum) throws AppException {
        Account accountTo = accountRepository.getAccountByStringId(stringIdAccountTo);
        if (accountTo == null) throw  new AppException( "no such account " + stringIdAccountTo);
        Bank bankTo = bankRepository.getBankById(accountTo.getBankId());

        Account accountFrom = accountRepository.getAccountById(accountFromId);
        if (accountFrom == null) throw  new AppException( "no such account" + accountFrom);
        Bank bankFrom = bankRepository.getBankById(accountFrom.getBankId());

        MoveMoneyTransaction moveMoneyTransaction = MoveMoneyTransaction.builder()
                .toAccount(accountTo)
                .fromAccount(accountFrom)
                .customer(customer)
                .sum(sum)
                .maxDoubtSumTo(bankTo.getMaxDoubtSum())
                .maxDoubtSumFrom(bankFrom.getMaxDoubtSum())
                .currentStateManager(currentStateManager)
                .build();
        transactionRepository.addTransaction(moveMoneyTransaction);

        try{
            return moveMoneyTransaction.execute();
        } catch (AppException exception){
            throw exception;
        }
    }

    /**
     * counts balance of the customer
     * @param account
     * @param days period
     * @return balance
     */
    public BigDecimal countBalanceOverPeriod(Account account, int days){
        return account.countBalanceOverPeriod(days, currentStateManager.getCurrentData());
    }
}
