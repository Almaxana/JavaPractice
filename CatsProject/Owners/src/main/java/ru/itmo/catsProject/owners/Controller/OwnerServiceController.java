package ru.itmo.catsProject.owners.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.catsProject.common.Dto.OwnerDto;
import ru.itmo.catsProject.owners.Core.OwnerService;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/ownerService")
public class OwnerServiceController {
    @Autowired
    private OwnerDtoModelMapper ownerMapper;
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/add")
    public void addOwner(@RequestBody OwnerDto ownerDto){
        ownerService.addOwner(ownerMapper.ownerDtoToModel(ownerDto));
    }

    @GetMapping("/getById/{ownerId}")
    public OwnerDto getOwnerById(@PathVariable("ownerId") Long ownerId){
        return ownerMapper.ownerModelToDto(ownerService.getOwnerModelById(ownerId));
    }

    @DeleteMapping("/delete/{ownerId}")
    public void deleteOwner (@PathVariable("ownerId") Long ownerId){
        ownerService.deleteOwner(ownerId);
    }

}
