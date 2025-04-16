package ru.itmo.Presenatation.Scenarios.CustomerIn.AccountIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.util.Scanner;

@AllArgsConstructor
public class ShowBalanceOverPeriodScenario implements IScenario {
    public AccountService accountService;
    public CurrentStateManager currentStateManager;
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter days number");
        int days = in.nextInt();


        System.out.println(accountService.countBalanceOverPeriod(currentStateManager.getCurrentAccount(), days));
    }

    @Override
    public String getName() {
        return "Show my balance over period";
    }
}
