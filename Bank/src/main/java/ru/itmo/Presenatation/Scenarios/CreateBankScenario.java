package ru.itmo.Presenatation.Scenarios;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Services.CentralBank;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class CreateBankScenario implements IScenario{
    public CentralBank centralBank;
    public CreateBankScenario(CentralBank _centralBank){
        centralBank = _centralBank;
    }
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter bank name");
        String name = in.nextLine();

        System.out.println("\nEnter maxDoubtSum");
        BigDecimal maxDoubtSum = in.nextBigDecimal();

        System.out.println("\nEnter commission");
        BigDecimal commission = in.nextBigDecimal();

        System.out.println("\nEnter debet percent");
        BigDecimal debetPercent = in.nextBigDecimal();

        System.out.println("\nEnter credit max negative sum");
        BigDecimal creditMaxNegativeSum = in.nextBigDecimal();


        System.out.println("\nEnter deposit percent border");
        BigDecimal depositPercentBorder = in.nextBigDecimal();

        UUID id = UUID.randomUUID();

        try{
            System.out.println(centralBank.createBank(name, maxDoubtSum, commission, debetPercent, id, creditMaxNegativeSum, depositPercentBorder).getMessage());
        } catch (AppException appException){
            System.out.println(appException.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Create bank";
    }
}
