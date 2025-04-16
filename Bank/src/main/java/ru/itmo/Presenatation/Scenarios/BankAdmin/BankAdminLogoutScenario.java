package ru.itmo.Presenatation.Scenarios.BankAdmin;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Services.CentralBank;
import ru.itmo.Presenatation.Scenarios.IScenario;

@AllArgsConstructor
public class BankAdminLogoutScenario implements IScenario {
    CentralBank centralBank;
    @Override
    public void run() {
        centralBank.bankAdminLogout();
        System.out.println("\nSUCCESS\n");
    }

    @Override
    public String getName() {
        return "Admin Logout";
    }
}
