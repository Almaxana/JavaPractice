package ru.itmo.DataAccess;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Customer;
import ru.itmo.Core.Repositories.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {

    private final List<Customer> customers;
    public CustomerRepository(){
        customers = new ArrayList<>();
    }
    @Override
    public Customer getCustomerByNameSurname(String name, String surname) {
        return customers.stream().filter(elem->elem.getName().equals(name) && elem.getSurname().equals(surname)).findFirst().orElse(null);
    }

    @Override
    public OperationResult addCustomer(Customer customer) throws AppException {
        if (getCustomerByNameSurname(customer.getName(), customer.getSurname()) != null) throw new AppException( "such customer already exists");
        customers.add(customer);

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }

    @Override
    public Customer getCustomerByStringId(String customerId) {
        return customers.stream().filter(elem->elem.getId().toString().equals(customerId)).findFirst().orElse(null);
    }
}
