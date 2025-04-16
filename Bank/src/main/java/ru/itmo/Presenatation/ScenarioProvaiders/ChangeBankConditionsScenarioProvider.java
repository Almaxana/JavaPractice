package ru.itmo.Presenatation.ScenarioProvaiders;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.Scenarios.BankAdmin.ChangeBankConditions;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class ChangeBankConditionsScenarioProvider implements IScenarioProvider{
    CurrentStateManager currentStateManager;
    CentralBank centralBank;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.adminedBank != null) return new ChangeBankConditions(centralBank);
        else return null;
    }
}
