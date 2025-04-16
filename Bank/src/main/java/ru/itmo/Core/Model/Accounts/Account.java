package ru.itmo.Core.Model.Accounts;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Describes bank account
 */
@SuperBuilder
@Data
public abstract class Account {

    @NonNull
    protected UUID accountId;
    protected BigDecimal currentSum;
    @NonNull
    protected UUID customerId;
    @NonNull
    protected UUID bankId;
    @NonNull
    @Builder.Default
    protected DateTime dateOfStart = new DateTime();

    /**
     * Counts money in account over some days
     * @param days period
     * @param currentData start data
     * @return balance over period of days
     */
    public abstract BigDecimal countBalanceOverPeriod(int days, DateTime currentData);

}
