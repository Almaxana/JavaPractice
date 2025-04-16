package ru.itmo.catsProject.gateway.User.DataAccess;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.catsProject.common.Enums.Role;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String userName;
    private String password;
    private Long ownerId;

    @Enumerated(EnumType.STRING)
    private Role role;
}
