package ru.itmo.Presenatation.Scenarios.BankAdmin;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.math.BigDecimal;
import java.util.Scanner;

@AllArgsConstructor
public class ChangeBankConditions implements IScenario {
    public CentralBank centralBank;

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

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

        String buffer = in.nextLine();
        System.out.println("\nEnter message for subscribers");
        String messageToSubscribers = in.nextLine();

        System.out.println(centralBank.changeConditions(maxDoubtSum, commission, debetPercent, creditMaxNegativeSum, depositPercentBorder, messageToSubscribers).getMessage());
    }

    @Override
    public String getName() {
        return "Change bank conditions";
    }
}
