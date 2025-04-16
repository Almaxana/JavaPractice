package ru.itmo.Core.Repositories;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Model.Customer;

public interface ICustomerRepository {

    /**
     * finds the customer in customer storage by his name
     * @param name of the customer
     * @param surname of the customer
     * @return customer
     */
    Customer getCustomerByNameSurname(String name, String surname);
    /**
     * adds the customer to the customer storage
     * @param customer
     * @return success or failure with message
     */
    OperationResult addCustomer(Customer customer) throws AppException;

    /**
     * finds customer by his sting id
     * @param customerId
     * @return customer
     */
    Customer getCustomerByStringId(String customerId);
}
