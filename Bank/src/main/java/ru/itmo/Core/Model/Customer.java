package ru.itmo.Core.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Describes bank user
 */
@Builder
@Data
public class Customer {
    @Builder.Default
    private List<Notification> notifications = new ArrayList<>();
    @NonNull
    private String name;
    @NonNull
    private String surname;
    private String address;
    private String passport;
    @NonNull
    private UUID id;

    /**
     * Gets notification for user from a bank
     * @param message from bank
     * @param bankName
     */
    public void notify(String message, String bankName){
        notifications.add(new Notification(false, message, bankName));
    }
}
