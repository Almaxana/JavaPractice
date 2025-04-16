package ru.itmo.Presenatation.ScenarioProvaiders;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.Scenarios.CreateBankScenario;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class CreateBankScenarioProvider implements IScenarioProvider {
    public CentralBank centralBank;
    public CurrentStateManager currentStateManager;
    @Override
    public IScenario tryGetScenario() {
        if (currentStateManager.getCustomerInSystem() == null && currentStateManager.adminedBank == null) return new CreateBankScenario(centralBank);

        return null;
    }
}
