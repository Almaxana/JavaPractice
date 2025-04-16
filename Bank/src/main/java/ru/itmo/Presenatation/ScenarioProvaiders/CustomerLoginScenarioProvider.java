package ru.itmo.Presenatation.ScenarioProvaiders;

import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.Scenarios.CustomerLoginScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

public class CustomerLoginScenarioProvider implements IScenarioProvider{
    public CurrentStateManager currentStateManager;
    public CustomerService customerService;

    public CustomerLoginScenarioProvider(CurrentStateManager _currentStateManager, CustomerService _customerService){
        currentStateManager = _currentStateManager;
        customerService = _customerService;
    }

    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() == null && currentStateManager.adminedBank == null) return new CustomerLoginScenario(customerService);
        else return null;
    }
}
