package ru.itmo.Presenatation.ScenarioProvaiders;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.Scenarios.BankAdmin.BankAdminLogoutScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class BankAdminLogoutScenarioProvider implements IScenarioProvider{
    CurrentStateManager currentStateManager;
    CentralBank centralBank;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.adminedBank != null) return new BankAdminLogoutScenario(centralBank);
        else return null;
    }
}
