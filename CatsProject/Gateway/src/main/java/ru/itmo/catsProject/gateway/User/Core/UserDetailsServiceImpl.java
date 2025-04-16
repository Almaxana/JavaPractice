package ru.itmo.catsProject.gateway.User.Core;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.catsProject.gateway.User.DataAccess.UserEntity;
import ru.itmo.catsProject.gateway.User.DataAccess.UserRepository;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserModelEntityMapper userModelEntityMapper;
    @Transactional
    public void createUser(UserDetailsImpl user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (user.getOwnerId() == null){
            throw new IllegalArgumentException("OwnerId can't be NULL");
        }
        userRepository.saveAndFlush(userModelEntityMapper.UserModelToEntity(user, user.getOwnerId()));
    }

    @Transactional
    public void deleteUser(String username) {
        UserEntity userEntity = userRepository.findByUserName(username);
        userRepository.delete(userEntity);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userModelEntityMapper.UserEntityToModel(userEntity);
    }

    @Transactional
    public UserDetailsImpl getUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userModelEntityMapper.UserEntityToModel(userEntity);
    }
}