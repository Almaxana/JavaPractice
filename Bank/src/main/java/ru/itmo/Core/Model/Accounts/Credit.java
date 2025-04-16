package ru.itmo.Core.Model.Accounts;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.joda.time.DateTime;
import ru.itmo.Core.Common.Constants;
import ru.itmo.Core.Common.Tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Kind of account
 */
@SuperBuilder
@Data
public class Credit extends Account{
    private BigDecimal maxNegativeSum;
    private BigDecimal commission;

    @Override
    public BigDecimal countBalanceOverPeriod(int days, DateTime currentData) {
        DateTime tempData = new Tools().cloneDateTime(currentData);
        tempData = tempData.plusDays(days);
        int lastMonth = tempData.getMonthOfYear();
        tempData = new DateTime(currentData.getYear(), currentData.getMonthOfYear(), currentData.getDayOfMonth(), currentData.getHourOfDay(), currentData.getMinuteOfHour());
        BigDecimal newBalance = currentSum;
        for (int i = 1; i <= days; ++i){
            tempData = tempData.plusDays(1);
            if (tempData.getDayOfMonth() == 0 && tempData.getMonthOfYear() == lastMonth) return newBalance;
            newBalance = newBalance.add(commission.divide(new BigDecimal(new Constants().DAYS_IN_YEAR), 10, RoundingMode.HALF_UP).divide((new BigDecimal(new Constants().ALL_PERCENTS)), 10, RoundingMode.HALF_UP).multiply(newBalance));
        }

        return newBalance;
    }
}
