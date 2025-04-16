package ru.itmo.Presenatation.ScenarioProvaiders.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.CustomerIn.CreateAccountScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class CreateAccountScenarioProvider implements IScenarioProvider {
    public CurrentStateManager currentStateManager;
    public AccountService accountService;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() != null && currentStateManager.getCurrentAccount() == null) return new CreateAccountScenario(accountService);

        return null;
    }
}
