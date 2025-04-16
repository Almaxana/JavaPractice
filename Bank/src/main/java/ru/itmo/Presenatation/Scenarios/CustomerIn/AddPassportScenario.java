package ru.itmo.Presenatation.Scenarios.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.util.Scanner;

@AllArgsConstructor
public class AddPassportScenario implements IScenario {
    CurrentStateManager currentStateManager;
    CustomerService customerService;
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nType your passport number");
        String passport = in.nextLine();

        customerService.addOptionalParameters(null, passport, currentStateManager.getCustomerInSystem());
        System.out.println("\nSUCCESS\n");
    }

    @Override
    public String getName() {
        return "Add my passport number";
    }
}
