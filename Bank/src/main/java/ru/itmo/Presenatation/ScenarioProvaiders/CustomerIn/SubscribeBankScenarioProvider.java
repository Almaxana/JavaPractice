package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.SubscribeBankScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class SubscribeBankScenarioProvider implements IScenarioProvider {
    CurrentStateManager currentStateManager;
    CentralBank centralBank;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() != null) return new SubscribeBankScenario(centralBank, currentStateManager);

        return null;
    }
}
