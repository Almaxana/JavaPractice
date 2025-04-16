package ru.itmo.Presenatation.Scenarios.CustomerIn.AccountIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.math.BigDecimal;
import java.util.Scanner;


@AllArgsConstructor
public class GetMoneyScenario implements IScenario {
    CurrentStateManager currentStateManager;
    AccountService accountService;
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter sum");
        BigDecimal sum = in.nextBigDecimal();

        try{
            System.out.println(accountService.GetMoney(currentStateManager.getCurrentAccount().getAccountId(), currentStateManager.getCustomerInSystem(), sum));
        } catch (AppException appException){
            System.out.println(appException.getMessage());
        }

    }

    @Override
    public String getName() {
        return "Get money";
    }
}
