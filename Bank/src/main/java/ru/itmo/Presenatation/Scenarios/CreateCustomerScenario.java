package ru.itmo.Presenatation.Scenarios;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Services.CustomerService;

import java.util.Scanner;
import java.util.UUID;

public class CreateCustomerScenario implements IScenario {

    public CreateCustomerScenario(CustomerService _customerService){
        customerService = _customerService;
    }
    public CustomerService customerService;
    public String name;
    public String surName;
    public String address;
    public String passport;
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter your name");
        name = in.nextLine();

        System.out.println("\nEnter your surname");
        surName = in.nextLine();

        System.out.println("\nEnter your address or type SKIP");
        String scan = in.nextLine();
        address = scan.equals("SKIP") ? null : scan;

        System.out.println("\nEnter your passport or type SKIP");
        scan = in.nextLine();
        passport = scan.equals("SKIP") ? null : scan;

        UUID id = UUID.randomUUID();

        try {
            System.out.println(customerService.createCustomer(name, surName, address, passport, id).getMessage());
            System.out.println("Your id is " + id);
        } catch (AppException appException){
            System.out.println(appException.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Create customer";
    }
}
