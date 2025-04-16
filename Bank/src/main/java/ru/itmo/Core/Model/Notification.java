package ru.itmo.Core.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

/**
 * Describes notification from a bank to a customer about changing the bank conditions
 */
@Builder
@Data
public class Notification {
    @Builder.Default
    private boolean isRead = false;
    private String text;
    @NonNull
    private String fromBank;
}
