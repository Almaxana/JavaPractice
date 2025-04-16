package ru.itmo.Presenatation.Scenarios.CustomerIn.AccountIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.math.BigDecimal;
import java.util.Scanner;
@AllArgsConstructor
public class MoveMoneyScenario implements IScenario {
    CurrentStateManager currentStateManager;
    AccountService accountService;
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter target account");
        String targetAccount  = in.nextLine();

        System.out.println("\nEnter sum");
        BigDecimal sum  = in.nextBigDecimal();

        try{
            accountService.moveMoney(currentStateManager.getCurrentAccount().getAccountId(), targetAccount, currentStateManager.getCustomerInSystem(), sum);
        } catch (AppException appException){
            System.out.println(appException.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Move money";
    }
}
