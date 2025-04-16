package ru.itmo.catsProject.gateway.Controllers;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.itmo.catsProject.common.Clients.OwnerClient;
import ru.itmo.catsProject.common.Dto.OwnerDto;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/owner")
@ComponentScan("ru.itmo.owners.Controller")
public class OwnerController {
    @Autowired
    private OwnerClient ownerClient;

    @PostMapping("/add")
    public void addOwner(@RequestBody OwnerDto ownerDto){
        ownerClient.addOwner(ownerDto);
    }

    @GetMapping("/getById/{ownerId}")
    public OwnerDto getOwnerById(@PathVariable("ownerId") Long ownerId){
        return ownerClient.getOwnerById(ownerId);
    }

    @DeleteMapping("/delete/{ownerId}")
    public void deleteOwner (@PathVariable("ownerId") Long ownerId){
        ownerClient.deleteOwner(ownerId);
    }

    @GetMapping("getOwnerOfCat/{catId}")
    public OwnerDto getCatOwner(@PathVariable("catId") Long catId){
        return ownerClient.getCatOwner(catId);
    }

}
