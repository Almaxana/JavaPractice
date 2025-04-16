package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn.AccountIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.AccountIn.ShowBalanceOverPeriodScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class ShowBalanceOverPeriodScenarioProvider implements IScenarioProvider {
    AccountService accountService;
    CurrentStateManager currentStateManager;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCurrentAccount() != null) return new ShowBalanceOverPeriodScenario(accountService, currentStateManager);

        return null;
    }
}
