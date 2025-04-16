package ru.itmo.Core.Repositories;

import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Model.Accounts.Account;
import ru.itmo.Core.Model.Customer;

import java.util.List;
import java.util.UUID;

public interface IAccountRepository {
    /**
     * adds account to account storage
     * @param account
     * @return success or failure with message
     */
    OperationResult addAccount(Account account);

    /**
     * finds accounts of the customer
     * @param customerId
     * @return the customer's accounts
     */
    List<Account> getAccountsByCustomerId(UUID customerId);

    /**
     * finds accounts by its id
     * @param accountId
     * @return account
     */
    Account getAccountById(UUID accountId);

    /**
     * finds accounts by its string id
     * @param stringAccountId
     * @return account
     */
    Account getAccountByStringId(String stringAccountId);


}
