package ru.itmo.Presenatation.Scenarios;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Services.CentralBank;

import java.util.Scanner;

public class BankAdminLoginScenario implements IScenario{
    public BankAdminLoginScenario(CentralBank _centralBank){
        centralBank = _centralBank;
    }
    public CentralBank centralBank;

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter bank name");
        String bankName = in.nextLine();

        try {
            System.out.println((centralBank.bankAdminLogin(bankName).getMessage()));
        } catch (AppException appException){
            System.out.println(appException.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Bank Admin Login";
    }
}
