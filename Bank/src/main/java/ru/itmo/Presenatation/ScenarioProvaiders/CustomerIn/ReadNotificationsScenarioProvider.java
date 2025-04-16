package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.ReadNotificationsScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class ReadNotificationsScenarioProvider implements IScenarioProvider {
    CurrentStateManager currentStateManager;
    CustomerService customerService;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() != null) return new ReadNotificationsScenario(customerService, currentStateManager);
        else return null;
    }
}
