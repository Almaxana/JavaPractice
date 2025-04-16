package ru.itmo.Core.Repositories;

import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Model.Transactions.Transaction;

public interface ITransactionRepository {
    /**
     * adds the transaction to the transaction storage
     * @param transaction
     */
    void addTransaction(Transaction transaction);
}
