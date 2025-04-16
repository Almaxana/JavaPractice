package ru.itmo.catsProject.common.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.catsProject.common.Dto.UserDto;

@FeignClient(name = "userService", url = "http://localhost:8080/userService")
public interface UserClient {
    @GetMapping("/getByUserName/{userName}")
    UserDto getByUserName(@PathVariable("userName") String userName);
}