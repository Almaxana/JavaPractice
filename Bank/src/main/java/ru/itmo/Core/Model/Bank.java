package ru.itmo.Core.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Describes bank
 */
@Builder
@Data
public class Bank {

    @Builder.Default
    private List<Customer> subscribers = new ArrayList<>();
    @NonNull
    private String name;
    @NonNull
    private UUID bankId;
    private BigDecimal fixedDebetPercent;
    private BigDecimal commission;
    private BigDecimal creditMaxNegativeSum;
    private BigDecimal maxDoubtSum;
    private BigDecimal depositPercentBorder;
    @Builder.Default
    private BigDecimal MONEY_DEPOSIT_BORDER = new BigDecimal(50000);
    @Builder.Default
    private BigDecimal DEPOSIT_BORDER_COEFFICIENT = new BigDecimal(2);

    /**
     * Counts deposit percent depending on its start sum
     * @param startSum
     * @return deposit percent
     */
    public BigDecimal countDepositPercent(BigDecimal startSum){
        if (startSum.compareTo(MONEY_DEPOSIT_BORDER) < 0) return depositPercentBorder;
        else return depositPercentBorder.multiply(DEPOSIT_BORDER_COEFFICIENT);
    }
}
