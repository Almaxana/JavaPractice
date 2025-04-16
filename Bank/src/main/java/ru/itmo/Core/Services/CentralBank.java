package ru.itmo.Core.Services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Bank;
import ru.itmo.Core.Model.Customer;
import ru.itmo.Core.Repositories.IBankRepository;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * service for making actions with banks
 */
@AllArgsConstructor
public class CentralBank {
    private IBankRepository bankRepository;
    private CurrentStateManager currentStateManager;
    public OperationResult createBank(String name, BigDecimal maxDoubtSum, BigDecimal commission, BigDecimal fixedDebetPercent, UUID id, BigDecimal creditMaxNegativeSum, BigDecimal depositPercentBorder) throws AppException {
        try{
            return bankRepository.addBank(Bank.builder()
                    .name(name).bankId(id)
                    .fixedDebetPercent(fixedDebetPercent)
                    .commission(commission)
                    .creditMaxNegativeSum(creditMaxNegativeSum)
                    .maxDoubtSum(maxDoubtSum)
                    .depositPercentBorder(depositPercentBorder)
                    .build());
        } catch (AppException appException){
            throw appException;
        }
    }

    /**
     * sets bank admin in current app state
     * @param bankName
     * @return success or failure with message
     */
    public OperationResult bankAdminLogin(String bankName) throws AppException {
        Bank bank = bankRepository.getBankByName(bankName);
        if ( bank == null) throw new AppException( "no such bank");
        currentStateManager.adminedBank = bank;

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");

    }

    /**
     * removes bank admin from current app state
     */
    public void bankAdminLogout(){
        currentStateManager.adminedBank = null;
    }

    /**
     * changes params of the bank
     * @param maxDoubtSum
     * @param commission
     * @param fixedDebetPercent
     * @param creditMaxNegativeSum
     * @param depositPercentBorder
     * @param messageToSubscribers
     * @return success or failure with message
     */
    public OperationResult changeConditions(BigDecimal maxDoubtSum, BigDecimal commission, BigDecimal fixedDebetPercent, BigDecimal creditMaxNegativeSum, BigDecimal depositPercentBorder, String messageToSubscribers){
        currentStateManager.adminedBank.setMaxDoubtSum(maxDoubtSum);
        currentStateManager.adminedBank.setCommission(commission);
        currentStateManager.adminedBank.setFixedDebetPercent(fixedDebetPercent);
        currentStateManager.adminedBank.setCreditMaxNegativeSum(creditMaxNegativeSum);
        currentStateManager.adminedBank.setDepositPercentBorder(depositPercentBorder);
        for (Customer subscriber : currentStateManager.adminedBank.getSubscribers()){
            subscriber.notify(messageToSubscribers, currentStateManager.adminedBank.getName());
        }

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }

    /**
     * make a customer subscriber of the bank
     * @param bankName
     * @param subscriber
     * @return success or failure with message
     */
    public OperationResult subscribeToBank(String bankName, Customer subscriber) throws AppException {
        Bank bank = bankRepository.getBankByName(bankName);
        if (bank == null) throw new AppException("no such bank");
        bank.getSubscribers().add(subscriber);

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }


}
