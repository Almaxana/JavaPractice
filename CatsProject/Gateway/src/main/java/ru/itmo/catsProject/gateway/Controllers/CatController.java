package ru.itmo.catsProject.gateway.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.catsProject.common.Clients.CatClient;
import ru.itmo.catsProject.common.Dto.CatDto;
import ru.itmo.catsProject.common.Enums.CatColor;
import ru.itmo.catsProject.common.Enums.Role;
import ru.itmo.catsProject.gateway.User.Core.UserDetailsImpl;
import ru.itmo.catsProject.gateway.User.Core.UserDetailsServiceImpl;

import java.util.List;

@RestController()
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/cat")
public class CatController {
    @Autowired
    private CatClient catClient;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/add")
    public void addCat(@RequestBody CatDto catDto){
        catClient.addCat(catDto);
    }

    @DeleteMapping("/delete/{catId}")
    public void deleteCat(@PathVariable("catId") Long catId){
        catClient.deleteCat(catId);
    }

    @PutMapping("/addOwner/{catId}/{ownerId}")
    public void addOwnerToCat(@PathVariable("catId") Long catId, @PathVariable("ownerId") Long ownerId){
        catClient.addOwnerToCat(catId, ownerId);
    }

    @PutMapping("/deleteFriend/{catId}/{catFriendId}")
    public void deleteCatFriend(@PathVariable("catId") Long catId, @PathVariable("catFriendId") Long catFriendId){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetailsImpl currentUser = userDetailsService.getUserByUsername(currentUserName);
        Long currentOwnerId = currentUser.getOwnerId();
        Role currentRole = Role.valueOf(currentUser.getAuthorities().iterator().next().toString());

        catClient.deleteCatFriend(catId, catFriendId, currentOwnerId, currentRole);
    }

    @GetMapping("getFriends/{catId}")
    public List<CatDto> getCatFriends(@PathVariable("catId") Long catId){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetailsImpl currentUser = userDetailsService.getUserByUsername(currentUserName);
        Long currentOwnerId = currentUser.getOwnerId();
        Role currentRole = Role.valueOf(currentUser.getAuthorities().iterator().next().toString());

        return catClient.getCatFriends(catId, currentOwnerId, currentRole);
    }

    @GetMapping("getById/{id}")
    public CatDto getCatById(@PathVariable("id") Long id){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetailsImpl currentUser = userDetailsService.getUserByUsername(currentUserName);
        Long currentOwnerId = currentUser.getOwnerId();
        Role currentRole = Role.valueOf(currentUser.getAuthorities().iterator().next().toString());

        return catClient.getCatById(id, currentOwnerId, currentRole);
    }

    @PutMapping("/addFriend/{catId}/{catFriendId}")
    public void addCatFriend(@PathVariable("catId") Long catId, @PathVariable("catFriendId") Long catFriendId){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetailsImpl currentUser = userDetailsService.getUserByUsername(currentUserName);
        Long currentOwnerId = currentUser.getOwnerId();
        Role currentRole = Role.valueOf(currentUser.getAuthorities().iterator().next().toString());

        catClient.addCatFriend(catId, catFriendId, currentOwnerId, currentRole);
    }

    @GetMapping("getByColor/{color}")
    public List<CatDto> getCatByColor(@PathVariable("color") CatColor color){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetailsImpl currentUser = userDetailsService.getUserByUsername(currentUserName);
        Long currentOwnerId = currentUser.getOwnerId();
        Role currentRole = Role.valueOf(currentUser.getAuthorities().iterator().next().toString());

        return catClient.getCatByColor(color, currentOwnerId, currentRole);
    }

}
