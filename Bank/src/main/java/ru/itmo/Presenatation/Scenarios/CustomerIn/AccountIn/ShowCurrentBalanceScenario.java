package ru.itmo.Presenatation.Scenarios.CustomerIn.AccountIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class ShowCurrentBalanceScenario implements IScenario {
    CurrentStateManager currentStateManager;
    @Override
    public void run() {
        System.out.println(currentStateManager.getCurrentAccount().getCurrentSum() + "\n");
    }

    @Override
    public String getName() {
        return "Show my current balance";
    }
}
