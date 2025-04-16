package ru.itmo;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Model.Bank;
import ru.itmo.Core.Model.Customer;
import ru.itmo.Core.Repositories.IAccountRepository;
import ru.itmo.Core.Repositories.IBankRepository;
import ru.itmo.Core.Repositories.ICustomerRepository;
import ru.itmo.Core.Repositories.ITransactionRepository;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.DataAccess.AccountRepository;
import ru.itmo.DataAccess.BanksRepository;
import ru.itmo.DataAccess.CustomerRepository;
import ru.itmo.DataAccess.TransactionRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class ApplicationTests {
    CurrentStateManager currentStateManager = new CurrentStateManager(new DateTime());
    ICustomerRepository customerRepository = new CustomerRepository();
    ITransactionRepository transactionRepository = new TransactionRepository();
    IBankRepository bankRepository = new BanksRepository();
    IAccountRepository accountRepository = new AccountRepository();
    AccountService accountService = new AccountService(accountRepository, bankRepository, currentStateManager, transactionRepository);
    CustomerService customerService = new CustomerService(customerRepository, currentStateManager);
    CentralBank centralBank = new CentralBank(bankRepository, currentStateManager);


    @Test
    void createLoginCustomerTest(){
        Assertions.assertDoesNotThrow(() -> customerService.createCustomer("Ks", "Alm", null, null, UUID.randomUUID()));

        Customer savedCustomer  = customerRepository.getCustomerByNameSurname("Ks", "Alm");
        Assertions.assertDoesNotThrow(() -> customerService.login(savedCustomer.getId().toString()));

        Assertions.assertSame(currentStateManager.getCustomerInSystem(), savedCustomer);
    }

    @Test
    void subscribingToBankTest(){
        Customer customer = Customer.builder().
                id(UUID.randomUUID())
                .name("Ks")
                .surname("Alm")
                .build();
        Assertions.assertDoesNotThrow(() -> centralBank.createBank("Sber", new BigDecimal(10000), new BigDecimal(10), new BigDecimal(5) , UUID.randomUUID(),new BigDecimal(50000).negate() ,new BigDecimal(3)));
        Assertions.assertDoesNotThrow(() -> centralBank.subscribeToBank("Sber", customer));

        Bank bank = bankRepository.getBankByName("Sber");
        Assertions.assertSame(bank.getSubscribers().get(0), customer);
    }

    @Test
    void getNotificationTest(){
        Customer customer = Customer.builder().
                id(UUID.randomUUID())
                .name("Ks")
                .surname("Alm")
                .build();
        Assertions.assertDoesNotThrow(() -> centralBank.createBank("Sber", new BigDecimal(10000), new BigDecimal(10), new BigDecimal(5) , UUID.randomUUID(),new BigDecimal(50000).negate() ,new BigDecimal(3)));
        Assertions.assertDoesNotThrow(() ->centralBank.subscribeToBank("Sber", customer));

        Assertions.assertDoesNotThrow(() ->centralBank.bankAdminLogin("Sber"));
        Assertions.assertDoesNotThrow(() ->centralBank.changeConditions(new BigDecimal(20000), new BigDecimal(13), new BigDecimal(5), new BigDecimal(30000).negate(), new BigDecimal(4), "Message from bank"));

        Assertions.assertEquals(customer.getNotifications().get(0).getText(), "Message from bank");
        Assertions.assertEquals(customer.getNotifications().get(0).getFromBank(), "Sber");
    }

    @Test
    void countDebetMoneyOverPeriod(){
        Assertions.assertDoesNotThrow(() ->centralBank.createBank("Sber", new BigDecimal(10000), new BigDecimal(10), new BigDecimal(5) , UUID.randomUUID(),new BigDecimal(50000).negate() ,new BigDecimal(3)));

        UUID customerId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->customerService.createCustomer("Ks", "Alm", null, null, customerId));
        Assertions.assertDoesNotThrow(() ->customerService.login(customerId.toString()));

        UUID accountId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->accountService.createDebetAccount(accountId, new BigDecimal(30000), "Sber"));
        currentStateManager.setCurrentAccount(accountRepository.getAccountById(accountId));

        double newBalance = accountService.countBalanceOverPeriod(currentStateManager.getCurrentAccount(), 90).doubleValue();
        Assertions.assertEquals(30372.126741168362, newBalance);
    }

    @Test
    void countDepositMoneyOverPeriod(){
        Assertions.assertDoesNotThrow(() ->centralBank.createBank("Sber", new BigDecimal(10000), new BigDecimal(10), new BigDecimal(5) , UUID.randomUUID(), new BigDecimal(50000).negate() ,new BigDecimal(3)));

        UUID customerId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->customerService.createCustomer("Ks", "Alm", null, null, customerId));
        Assertions.assertDoesNotThrow(() ->customerService.login(customerId.toString()));

        UUID accountId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->accountService.createDepositAccount(accountId, new BigDecimal(30000), "Sber", 100));
        currentStateManager.setCurrentAccount(accountRepository.getAccountById(accountId));

        double newBalance = accountService.countBalanceOverPeriod(currentStateManager.getCurrentAccount(), 90).doubleValue();
        Assertions.assertEquals(30222.731492774696, newBalance);
    }

    @Test
    void countCreditMoneyOverPeriod(){
        Assertions.assertDoesNotThrow(() ->centralBank.createBank("Sber", new BigDecimal(10000), new BigDecimal(10), new BigDecimal(5) , UUID.randomUUID(), new BigDecimal(50000).negate() ,new BigDecimal(3)));

        UUID customerId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->customerService.createCustomer("Ks", "Alm", null, null, customerId));
        Assertions.assertDoesNotThrow(() ->customerService.login(customerId.toString()));

        UUID accountId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->accountService.createCreditAccount(accountId, new BigDecimal(1000).negate(), "Sber"));
        currentStateManager.setCurrentAccount(accountRepository.getAccountById(accountId));

        double newBalance = accountService.countBalanceOverPeriod(currentStateManager.getCurrentAccount(), 90).doubleValue();
        Assertions.assertEquals(-1024.9605836470407, newBalance);
    }

    @Test
    void earlyDepositMoneyGetting(){
        Assertions.assertDoesNotThrow(() ->centralBank.createBank("Sber", new BigDecimal(10000), new BigDecimal(10), new BigDecimal(5) ,UUID.randomUUID(), new BigDecimal(50000).negate(),new BigDecimal(3)));

        UUID customerId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->customerService.createCustomer("Ks", "Alm", null, null, customerId));
        Assertions.assertDoesNotThrow(() ->customerService.login(customerId.toString()));

        UUID accountId = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() ->accountService.createDepositAccount(accountId, new BigDecimal(10000), "Sber", 20));
        currentStateManager.setCurrentAccount(accountRepository.getAccountById(accountId));

        Exception exception = Assertions.assertThrows(AppException.class, () -> {OperationResult result = accountService.GetMoney(accountId, currentStateManager.getCustomerInSystem(), new BigDecimal(1000).negate());});
        Assertions.assertEquals(exception.getMessage(), "too early to get money from deposit");
    }





}
