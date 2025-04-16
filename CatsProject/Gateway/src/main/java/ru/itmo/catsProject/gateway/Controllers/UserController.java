package ru.itmo.catsProject.gateway.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.catsProject.common.Dto.UserDto;
import ru.itmo.catsProject.gateway.User.Controller.UserDtoModelMapper;
import ru.itmo.catsProject.gateway.User.Core.UserDetailsServiceImpl;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserDtoModelMapper userMapper;

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto){
        userDetailsService.createUser(userMapper.UserDtoToModel(userDto));
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUser (@PathVariable("username") String username){
        userDetailsService.deleteUser(username);
    }

}