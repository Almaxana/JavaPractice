package ru.itmo.Presenatation.Scenarios.CustomerIn;

import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.Scenarios.IScenario;

public class CustomerLogoutScenario implements IScenario {
    public CustomerLogoutScenario(CustomerService _customerService){
        customerService = _customerService;
    }
    public CustomerService customerService;

    @Override
    public void run() {
        customerService.logout();
        System.out.println("\nSUCCESS\n");
    }

    @Override
    public String getName() {
        return "Customer Logout";
    }
}
