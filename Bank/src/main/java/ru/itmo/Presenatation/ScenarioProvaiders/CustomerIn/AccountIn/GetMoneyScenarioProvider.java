package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn.AccountIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.AccountIn.GetMoneyScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class GetMoneyScenarioProvider implements IScenarioProvider {
    CurrentStateManager currentStateManager;
    AccountService accountService;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCurrentAccount() != null) return new GetMoneyScenario(currentStateManager, accountService);

        return null;
    }
}
