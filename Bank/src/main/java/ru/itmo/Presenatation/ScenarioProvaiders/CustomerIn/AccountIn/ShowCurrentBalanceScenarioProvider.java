package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn.AccountIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.AccountIn.ShowCurrentBalanceScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class ShowCurrentBalanceScenarioProvider implements IScenarioProvider {
    CurrentStateManager currentStateManager;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCurrentAccount() != null) return new ShowCurrentBalanceScenario(currentStateManager);

        return null;
    }
}
