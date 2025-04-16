package ru.itmo.Core.Repositories;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Model.Bank;

import java.util.UUID;

public interface IBankRepository {
    /**
     * finds the bank in bank storage by its name
     * @param name of the bank
     * @return bank
     */
    Bank getBankByName(String name);

    /**
     * finds the bank in bank storage by its name
     * @param bankId of the bank
     * @return bank
     */
    Bank getBankById(UUID bankId);

    /**
     * adds the bank to the bank storage
     * @param bank
     * @return success or failure with message
     */
    OperationResult addBank(Bank bank) throws AppException;

}
