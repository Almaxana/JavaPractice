package ru.itmo.Presenatation.Scenarios.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.util.Scanner;
@AllArgsConstructor
public class SubscribeBankScenario implements IScenario {
    CentralBank centralBank;
    CurrentStateManager currentStateManager;
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter bank name");
        String bankName = in.nextLine();

        try{
            System.out.println((centralBank.subscribeToBank(bankName, currentStateManager.getCustomerInSystem()).getMessage()));
        } catch (AppException appException){
            System.out.println(appException.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Subscribe a bank";
    }
}
