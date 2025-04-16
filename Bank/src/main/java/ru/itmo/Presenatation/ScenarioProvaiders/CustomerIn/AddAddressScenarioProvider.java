package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.AddAddressScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class AddAddressScenarioProvider implements IScenarioProvider {
    CurrentStateManager currentStateManager;
    CustomerService customerService;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() != null && currentStateManager.getCustomerInSystem().getAddress() == null) return new AddAddressScenario(currentStateManager, customerService);
        return null;
    }
}
