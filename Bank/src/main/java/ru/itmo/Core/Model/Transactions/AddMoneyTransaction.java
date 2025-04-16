package ru.itmo.Core.Model.Transactions;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Accounts.Account;

/**
 * Adds money to account
 */
@SuperBuilder
@Data
public class AddMoneyTransaction extends Transaction{
    private Account account;
    @Override
    public OperationResult execute() throws AppException {
        if (done) throw new AppException("transaction can't be executed twice");
        done = true;
        account.setCurrentSum(account.getCurrentSum().add(sum));
        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }

    @Override
    public void undo() {
        if (!canceled) {
            account.setCurrentSum(account.getCurrentSum().subtract(sum));
            canceled = true;
        }
    }
}
