package ru.itmo.Presenatation.ScenarioProvaiders;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.Scenarios.BankAdminLoginScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class BankAdminLoginScenarioProvider implements IScenarioProvider {
    public CurrentStateManager currentStateManager;
    public CentralBank centralBank;

    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.adminedBank == null && currentStateManager.getCustomerInSystem() == null) return new BankAdminLoginScenario(centralBank);
        else return null;
    }
}
