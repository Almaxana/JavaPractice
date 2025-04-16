package ru.itmo.catsProject.cats.Controller;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.catsProject.cats.Core.CatService;
import ru.itmo.catsProject.common.Dto.CatDto;
import ru.itmo.catsProject.common.Enums.CatColor;
import ru.itmo.catsProject.common.Enums.Role;

import java.util.List;


@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/catService")
public class CatServiceController {
    @Autowired
    private CatService catService;

    @Autowired
    private CatDtoModelMapper catMapper;

    @PostMapping("/add")
    public void addCat(@RequestBody CatDto catDto){
        catService.addCat(catMapper.catDtoToModel(catDto));
    }

    @DeleteMapping("/delete/{catId}")
    public void deleteCat(@PathVariable("catId") Long catId) throws Exception {
        catService.deleteCat(catId);
    }


    @PutMapping("/addFriend/{catId}/{catFriendId}/{currentUserId}/{currentUserRole}")
    void addCatFriend(@PathVariable("catId") Long catId, @PathVariable("catFriendId") Long catFriendId, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole") Role currentUserRole) throws Exception {
        catService.addFriendToCat(catId, catFriendId, currentUserId, currentUserRole);
    }

    @PutMapping("/deleteFriend/{catId}/{catFriendId}/{currentUserId}/{currentUserRole}")
    void deleteCatFriend(@PathVariable("catId") Long catId, @PathVariable("catFriendId") Long catFriendId, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole")Role currentUserRole) throws Exception {
        catService.deleteCatFriend(catId, catFriendId, currentUserId, currentUserRole);
    }

    @GetMapping("getFriends/{catId}/{currentUserId}/{currentUserRole}")
    List<CatDto> getCatFriends(@PathVariable("catId") Long catId, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole") Role currentUserRole) throws Exception {
        return catService.getCatFriends(catId, currentUserId, currentUserRole).stream().map(cat -> catMapper.catModelToDto(cat)).toList();
    }

    @GetMapping("getById/{id}/{currentUserId}/{currentUserRole}")
    CatDto getCatById(@PathVariable("id") Long id, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole") Role currentUserRole) throws Exception {
        return catMapper.catModelToDto(catService.getCatModelById(id, currentUserId, currentUserRole));
    }

    @GetMapping("getByOwnerId/{id}")
    public List<CatDto> getByOwnerId(@PathVariable("id") Long id){
        return catService.getOwnerCats(id).stream().map(cat -> catMapper.catModelToDto(cat)).toList();
    }

    @PutMapping("addOwner/{catId}/")
    public void addOwnerToCat(@PathVariable("catId") Long catId, @RequestParam(value = "ownerId", required = false) Long ownerId){
        catService.addOwnerToCat(catId, ownerId);
    }

    @GetMapping("getByColor/{color}/{currentUserId}/{currentUserRole}")
    List<CatDto> getCatByColor(@PathVariable("color") CatColor color, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole")Role currentUserRole){
        return catService.getByColor(color, currentUserId, currentUserRole).stream().map(cat -> catMapper.catModelToDto(cat)).toList();
    }
}

