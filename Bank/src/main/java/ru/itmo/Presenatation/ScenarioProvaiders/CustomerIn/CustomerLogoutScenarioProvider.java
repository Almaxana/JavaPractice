package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn;

import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.CustomerLogoutScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

public class CustomerLogoutScenarioProvider implements IScenarioProvider {
    public CurrentStateManager currentStateManager;
    public CustomerService customerService;

    public CustomerLogoutScenarioProvider(CurrentStateManager _currentStateManager, CustomerService _customerService){
        currentStateManager = _currentStateManager;
        customerService = _customerService;
    }

    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() != null) return new CustomerLogoutScenario(customerService);
        else return null;
    }
}
