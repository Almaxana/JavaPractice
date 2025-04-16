package ru.itmo.Core.Model.Transactions;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Common.OperationResult;

import java.math.BigDecimal;

/**
 * Makes any action with money in account
 */
@SuperBuilder
@Data
public abstract class Transaction {
    protected BigDecimal sum;
    @Builder.Default
    protected boolean canceled = false;
    @Builder.Default
    protected boolean done = false;
    @NonNull
    CurrentStateManager currentStateManager;

    /**
     * Makes action with money
     * @return result type
     */
    public abstract OperationResult execute() throws AppException;

    /**
     * cancels taken action with money
     */
    public abstract void undo();
}
