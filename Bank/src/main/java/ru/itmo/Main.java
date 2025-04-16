package ru.itmo;

import org.joda.time.DateTime;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Model.Accounts.Debet;
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
import ru.itmo.Presenatation.ScenarioProvaiders.*;
import ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn.*;
import ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn.AccountIn.*;
import ru.itmo.Presenatation.ScenarioRunner;
import ru.itmo.Presenatation.Scenarios.CustomerIn.AddPassportScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        CurrentStateManager currentStateManager = new CurrentStateManager();
        currentStateManager.setCurrentData(new DateTime());
        ICustomerRepository customerRepository = new CustomerRepository();
        ITransactionRepository transactionRepository = new TransactionRepository();
        IBankRepository bankRepository = new BanksRepository();
        IAccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository, bankRepository, currentStateManager, transactionRepository);
        CustomerService customerService = new CustomerService(customerRepository, currentStateManager);
        CentralBank centralBank = new CentralBank(bankRepository, currentStateManager);


        List<IScenarioProvider> providers = new ArrayList<>();
        providers.add(new CreateCustomerScenarioProvider(currentStateManager, customerService));
        providers.add(new CustomerLoginScenarioProvider(currentStateManager, customerService));
        providers.add(new CustomerLogoutScenarioProvider(currentStateManager, customerService));
        providers.add(new CreateAccountScenarioProvider(currentStateManager, accountService));
        providers.add(new CreateBankScenarioProvider(centralBank, currentStateManager));
        providers.add(new SelectAccountScenarioProvider(currentStateManager, accountService));
        providers.add(new AddMoneyScenarioProvider(currentStateManager, accountService));
        providers.add(new GetMoneyScenarioProvider(currentStateManager, accountService));
        providers.add(new MoveMoneyScenarioProvider(currentStateManager, accountService));
        providers.add(new ShowCurrentBalanceScenarioProvider(currentStateManager));
        providers.add(new ShowBalanceOverPeriodScenarioProvider(accountService, currentStateManager));
        providers.add(new BankAdminLoginScenarioProvider(currentStateManager, centralBank));
        providers.add((new ChangeBankConditionsScenarioProvider(currentStateManager, centralBank)));
        providers.add((new ReadNotificationsScenarioProvider(currentStateManager, customerService)));
        providers.add(new BankAdminLogoutScenarioProvider(currentStateManager, centralBank));
        providers.add((new SubscribeBankScenarioProvider(currentStateManager, centralBank)));
        providers.add((new AddPassportScenarioProvider(currentStateManager, customerService)));
        providers.add((new AddAddressScenarioProvider(currentStateManager, customerService)));


        ScenarioRunner scenarioRunner = new ScenarioRunner(providers);
        while (true){
            scenarioRunner.run();
            currentStateManager.setCurrentData(new DateTime());
        }

    }
}