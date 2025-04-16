package ru.itmo.DataAccess;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Bank;
import ru.itmo.Core.Repositories.IBankRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BanksRepository implements IBankRepository {
    private final List<Bank> banks;
    public BanksRepository(){
        banks = new ArrayList<>();
    }
    @Override
    public Bank getBankByName(String name) {
        return banks.stream().filter(elem->elem.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Bank getBankById(UUID bankId) {
        return banks.stream().filter(elem->elem.getBankId() == bankId).findFirst().orElse(null);
    }

    @Override
    public OperationResult addBank(Bank bank) throws AppException {
        if (getBankByName(bank.getName()) != null) throw new AppException( "such bank already exists");
        banks.add(bank);

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }
}
