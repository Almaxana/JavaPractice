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
 * Move money from one account to another
 */
@SuperBuilder
@Data
public class MoveMoneyTransaction extends Transaction{
    @NonNull
    private Account fromAccount;
    @NonNull
    private Account toAccount;
    @NonNull
    private Customer customer;
    private BigDecimal maxDoubtSumTo;
    private BigDecimal maxDoubtSumFrom;
    @Override
    public OperationResult execute() throws AppException {
        if (done) throw  new AppException( "transaction can't be executed twice");
        done = true;
        if ((customer.getAddress() == null || customer.getPassport() == null) && (sum.compareTo(maxDoubtSumTo) > 0 || sum.compareTo(maxDoubtSumFrom) > 0)) {
            throw  new AppException( "exceed max doubt sum limit");
        }

        if (fromAccount instanceof Debet debet) {
            if (debet.getCurrentSum().compareTo(sum) >= 0) {
                fromAccount.setCurrentSum(fromAccount.getCurrentSum().subtract(sum));
                toAccount.setCurrentSum(toAccount.getCurrentSum().add(sum));
                return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
            }
            return new OperationResult(OperationResultVerdict.SUCCESS, "no enough money");
        }

        if (fromAccount instanceof Deposit deposit){
            if (Days.daysBetween(deposit.getDateOfStart(), currentStateManager.getCurrentData()).getDays() < deposit.getLength()) throw  new AppException( "too early to get money from deposit");
            if (deposit.getCurrentSum().compareTo(sum) < 0) throw  new AppException( "no enough money");

            fromAccount.setCurrentSum(fromAccount.getCurrentSum().subtract(sum));
            toAccount.setCurrentSum(toAccount.getCurrentSum().add(sum));

            return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
        }

        if (fromAccount instanceof Credit credit){
            if (fromAccount.getCurrentSum().subtract(sum).compareTo(credit.getMaxNegativeSum()) < 0) throw  new AppException( "exceed max negative sum limit");

            fromAccount.setCurrentSum(fromAccount.getCurrentSum().subtract(sum));
            toAccount.setCurrentSum(toAccount.getCurrentSum().add(sum));
            return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");

        }

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }

    @Override
    public void undo() {
        if (canceled) return;
        fromAccount.setCurrentSum(fromAccount.getCurrentSum().add(sum));
        toAccount.setCurrentSum(toAccount.getCurrentSum().subtract(sum));
        canceled = true;
    }
}
