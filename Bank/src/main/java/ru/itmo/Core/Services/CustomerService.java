package ru.itmo.Core.Services;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Model.Customer;
import ru.itmo.Core.Model.Notification;
import ru.itmo.Core.Repositories.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * do operations, related with customers
 */
@AllArgsConstructor
public class CustomerService{
    private final ICustomerRepository customerRepository;
    private final CurrentStateManager currentStateManager;

    /**
     * add a customer to system
     * @param name
     * @param surname
     * @param address
     * @param passport
     * @param id
     * @return success or failure with message
     */
    public OperationResult createCustomer(String name, String surname, String address, String passport, UUID id) throws AppException {
        try {
            return customerRepository.addCustomer(Customer.builder()
                    .name(name).surname(surname)
                    .address(address)
                    .passport(passport)
                    .id(id)
                    .build());
        } catch (AppException appException){
            throw appException;
        }
    }

    /**
     * set the customer in current app state
     * @param customerId
     * @return success or failure with message
     */
    public OperationResult login(String customerId) throws AppException {
        Customer customer = customerRepository.getCustomerByStringId(customerId);
        if ( customer == null) throw new AppException( "no such customer");
        currentStateManager.setCustomerInSystem(customer);

        return new OperationResult(OperationResultVerdict.SUCCESS, "SUCCESS");
    }

    /**
     * removes the customer from current app state
     */
    public void logout() {
        currentStateManager.setCustomerInSystem(null);
        currentStateManager.setCurrentAccount(null);
    }

    /**
     * adds params to customer account after signing in
     * @param address
     * @param passport
     * @param customer
     */
    public void addOptionalParameters(String address, String passport, Customer customer){
        if (address != null) customer.setAddress(address);
        if (passport != null) customer.setPassport(passport);
    }

    /**
     * gets list of messages to a customer from his banks
     * @param customer
     * @return messages
     */
    public List<String> getNotifications(Customer customer){
        boolean noMessages = true;
        List<String> notifications = new ArrayList<>();
        for (Notification notification : customer.getNotifications()){
            if (!notification.isRead()) {
                notifications.add("\n" + notification.getText() + "\n" + "FROM" + notification.getFromBank() + "\n\n\n");
                noMessages = false;
                notification.setRead(true);
            }
        }
        if (noMessages) notifications.add("\n No new messages \n\n\n");

        return notifications;
    }


}
