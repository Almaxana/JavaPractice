package ru.itmo.catsProject.common.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.itmo.catsProject.common.Dto.CatDto;
import ru.itmo.catsProject.common.Enums.CatColor;
import ru.itmo.catsProject.common.Enums.Role;

import java.util.List;

@FeignClient(name = "catService", url = "http://localhost:8082/catService")
public interface CatClient {
    @PostMapping("/add")
    void addCat(@RequestBody CatDto catDto);

    @DeleteMapping("/delete/{catId}")
    void deleteCat(@PathVariable("catId") Long catId);

    @PutMapping("/addFriend/{catId}/{catFriendId}/{currentUserId}/{currentUserRole}")
    void addCatFriend(@PathVariable("catId") Long catId, @PathVariable("catFriendId") Long catFriendId, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole") Role currentUserRole);

    @PutMapping("addOwner/{catId}/")
    void addOwnerToCat(@PathVariable("catId") Long catId, @RequestParam(value = "ownerId", required = false) Long ownerId);

    @PutMapping("/deleteFriend/{catId}/{catFriendId}/{currentUserId}/{currentUserRole}")
    void deleteCatFriend(@PathVariable("catId") Long catId, @PathVariable("catFriendId") Long catFriendId, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole")Role currentUserRole);

    @GetMapping("getFriends/{catId}/{currentUserId}/{currentUserRole}")
    List<CatDto> getCatFriends(@PathVariable("catId") Long catId, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole")Role currentUserRole);

    @GetMapping("getById/{id}/{currentUserId}/{currentUserRole}")
    CatDto getCatById(@PathVariable("id") Long id, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole") Role currentUserRole);

    @GetMapping("getByOwnerId/{id}")
    List<CatDto> getByOwnerId(@PathVariable("id") Long id);

    @GetMapping("getByColor/{color}/{currentUserId}/{currentUserRole}")
    List<CatDto> getCatByColor(@PathVariable("color") CatColor color, @PathVariable("currentUserId") Long currentUserId, @PathVariable("currentUserRole")Role currentUserRole);
}
