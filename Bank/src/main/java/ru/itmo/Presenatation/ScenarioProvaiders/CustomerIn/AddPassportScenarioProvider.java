package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.AddPassportScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class AddPassportScenarioProvider implements IScenarioProvider {
    CurrentStateManager currentStateManager;
    CustomerService customerService;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() != null && currentStateManager.getCustomerInSystem().getPassport() == null) return new AddPassportScenario(currentStateManager, customerService);
        return null;
    }
}
