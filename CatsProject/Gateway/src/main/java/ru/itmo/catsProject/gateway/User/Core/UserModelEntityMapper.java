package ru.itmo.catsProject.gateway.User.Core;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itmo.catsProject.common.Enums.Role;
import ru.itmo.catsProject.gateway.User.DataAccess.UserEntity;

import java.util.Collections;

@Component
public class UserModelEntityMapper {
    public UserEntity UserModelToEntity(UserDetails userDetails, Long ownerId){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDetails.getUsername());
        userEntity.setPassword(userDetails.getPassword());
        userEntity.setOwnerId(ownerId);
        userEntity.setRole(Role.valueOf(userDetails.getAuthorities().iterator().next().toString()));

        return userEntity;
    }

    public UserDetailsImpl UserEntityToModel(UserEntity userEntity){
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setPassword(userEntity.getPassword());
        userDetails.setUsername(userEntity.getUserName());
        if (userEntity.getOwnerId() == null) {
            userDetails.setOwnerId(null);
        }
        else {
            userDetails.setOwnerId(userEntity.getOwnerId());
        }
        userDetails.setAuthorities(Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().toString())));

        return userDetails;
    }
}
