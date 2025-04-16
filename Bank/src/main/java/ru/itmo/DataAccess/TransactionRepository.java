package ru.itmo.DataAccess;

import ru.itmo.Core.Model.Transactions.Transaction;
import ru.itmo.Core.Repositories.ITransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements ITransactionRepository {
    public TransactionRepository(){
        transactions = new ArrayList<>();
    }
    List<Transaction> transactions;
    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
