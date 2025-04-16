package ru.itmo.Presenatation.Scenarios.CustomerIn;

import lombok.AllArgsConstructor;
import ru.itmo.Core.Common.CurrentStateManager;
import ru.itmo.Core.Model.Notification;
import ru.itmo.Core.Services.CustomerService;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ReadNotificationsScenario implements IScenario {
    CustomerService customerService;
    CurrentStateManager currentStateManager;
    @Override
    public void run() {
        List<String> notifications = customerService.getNotifications(currentStateManager.getCustomerInSystem());
        for (String message : notifications){
            System.out.println(message);
        }

    }

    @Override
    public String getName() {
        return "Read my notifications";
    }
}
