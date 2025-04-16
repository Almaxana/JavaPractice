package ru.itmo.DataAccess;

import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Accounts.Account;
import ru.itmo.Core.Repositories.IAccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AccountRepository implements IAccountRepository {
    private final List<Account> accounts;
    public AccountRepository(){
        accounts = new ArrayList<>();
    }
    @Override
    public OperationResult addAccount(Account account) {
        accounts.add(account);
        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }

    @Override
    public List<Account> getAccountsByCustomerId(UUID customerId) {
        return accounts.stream().filter(account -> account.getCustomerId() == customerId).collect(Collectors.toList());
    }

    @Override
    public Account getAccountById(UUID accountId) {
        return accounts.stream().filter(account -> account.getAccountId() == accountId).findFirst().orElse(null);
    }

    @Override
    public Account getAccountByStringId(String stringAccountId) {
        return accounts.stream().filter(account -> account.getAccountId().toString().equals(stringAccountId)).findFirst().orElse(null);
    }

}
