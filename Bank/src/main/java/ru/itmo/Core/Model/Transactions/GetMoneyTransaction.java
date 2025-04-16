package ru.itmo.Core.Model.Transactions;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.joda.time.Days;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Accounts.Account;
import ru.itmo.Core.Model.Accounts.Credit;
import ru.itmo.Core.Model.Accounts.Debet;
import ru.itmo.Core.Model.Accounts.Deposit;
import ru.itmo.Core.Model.Customer;

import java.math.BigDecimal;

/**
 * Takes money to account
 */
@SuperBuilder
@Data
public class GetMoneyTransaction extends Transaction{
    @NonNull
    private Account account;
    @NonNull
    private Customer customer;
    private BigDecimal maxDoubtSum;
    @Override
    public OperationResult execute() throws AppException {
        if (done) throw new AppException("transaction can't be executed twice");
        done = true;
        if ((customer.getAddress() == null || customer.getPassport() == null) && sum.compareTo(maxDoubtSum) > 0) throw  new AppException( "exceed max negative sum limit");

        if (account instanceof Debet debet) {
            if (debet.getCurrentSum().compareTo(sum) < 0) throw  new AppException("no enough money");

            account.setCurrentSum(account.getCurrentSum().subtract(sum));
            return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");

        }
        if (account instanceof Deposit deposit){
            if (Days.daysBetween(deposit.getDateOfStart(), currentStateManager.getCurrentData()).getDays() < deposit.getLength()) throw  new AppException("too early to get money from deposit");
            account.setCurrentSum(account.getCurrentSum().subtract(sum));
            return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");

        }
        if (account instanceof Credit credit){
            if (account.getCurrentSum().subtract(sum).compareTo(credit.getMaxNegativeSum()) <= 0) throw  new AppException( "exceed max negative sum limit");
            account.setCurrentSum(account.getCurrentSum().subtract(sum));
            return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");

        }

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }

    @Override
    public void undo() {

        if(!canceled){
            account.setCurrentSum(account.getCurrentSum().add(sum));
            canceled = true;
        }
    }
}
