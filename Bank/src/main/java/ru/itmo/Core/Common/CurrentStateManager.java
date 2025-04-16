package ru.itmo.Core.Common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import ru.itmo.Core.Model.Accounts.Account;
import ru.itmo.Core.Model.Bank;
import ru.itmo.Core.Model.Customer;

/**
 * Contains current application state
 */
@NoArgsConstructor
@Data
public class CurrentStateManager {
    public CurrentStateManager(DateTime dataTime){
            currentData = dataTime;
    }
    private Customer customerInSystem;
    private Account currentAccount;
    private DateTime currentData;
    public Bank adminedBank;
}
