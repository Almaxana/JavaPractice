package ru.itmo.catsProject.common.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.itmo.catsProject.common.Dto.OwnerDto;

@FeignClient(name = "ownerService", url = "http://localhost:8081/ownerService")
public interface OwnerClient {
    @PostMapping("/add")
    void addOwner(@RequestBody OwnerDto ownerDto);

    @GetMapping("/getById/{ownerId}")
    OwnerDto getOwnerById(@PathVariable("ownerId") Long ownerId);

    @DeleteMapping("/delete/{ownerId}")
    void deleteOwner (@PathVariable("ownerId") Long ownerId);

    @GetMapping("getOwnerOfCat/{catId}")
    OwnerDto getCatOwner(@PathVariable("catId") Long catId);

}
