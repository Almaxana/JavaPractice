package ru.itmo.Presenatation.Scenarios;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Services.CustomerService;

import java.util.Scanner;

public class CustomerLoginScenario implements IScenario{
    public CustomerLoginScenario(CustomerService _customerService){
        customerService = _customerService;
    }
    public CustomerService customerService;



    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter your id");
        String customerId = in.nextLine();


        try{
            System.out.println(customerService.login(customerId).getMessage());
        } catch (AppException appException){
            System.out.println(appException.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Customer Login";
    }
}
