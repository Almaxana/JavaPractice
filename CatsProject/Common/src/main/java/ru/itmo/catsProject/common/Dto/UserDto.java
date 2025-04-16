package ru.itmo.catsProject.common.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.catsProject.common.Enums.Role;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private Long ownerId;
    private Role role;
}
