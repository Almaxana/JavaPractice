package ru.itmo.catsProject.gateway.User.Controller;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.itmo.catsProject.common.Dto.UserDto;
import ru.itmo.catsProject.common.Enums.Role;
import ru.itmo.catsProject.gateway.User.Core.UserDetailsImpl;

import java.util.Collections;

@Component
public class UserDtoModelMapper {
    public UserDetailsImpl UserDtoToModel(UserDto userDto){
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(userDto.getUsername());
        userDetails.setPassword(userDto.getPassword());
        userDetails.setOwnerId(userDto.getOwnerId());
        userDetails.setAuthorities(Collections.singleton(new SimpleGrantedAuthority(userDto.getRole().toString())));

        return userDetails;
    }

    public UserDto UserModelToDto(UserDetailsImpl userDetails){
        return new UserDto(userDetails.getUsername(), userDetails.getPassword(), userDetails.getOwnerId(), Role.valueOf(userDetails.getAuthorities().toString()));
    }
}
