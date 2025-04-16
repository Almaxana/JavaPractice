package ru.itmo.Presenatation.ScenarioProvaiders;

import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.Scenarios.CreateCustomerScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

public class CreateCustomerScenarioProvider implements IScenarioProvider{
    public CurrentStateManager currentStateManager;
    public CustomerService customerService;

    public CreateCustomerScenarioProvider(CurrentStateManager _currentStateManager, CustomerService _customerService){
        currentStateManager = _currentStateManager;
        customerService = _customerService;
    }

    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() == null && currentStateManager.adminedBank == null) return new CreateCustomerScenario(customerService);
        else return null;
    }
}
