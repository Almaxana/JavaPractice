package ru.itmo.catsProject.cats.Core;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.catsProject.cats.DataAccess.CatEntity;
import ru.itmo.catsProject.cats.DataAccess.CatRepository;
import ru.itmo.catsProject.common.Enums.CatColor;
import ru.itmo.catsProject.common.Enums.Role;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    @Autowired
    private CatModelEntityMapper catMapper;
    @Transactional
    public CatModel getCatModelById(long catId, Long currentOwnerId, Role currentRole) throws Exception {

        if (currentRole != Role.ADMIN && (catRepository.getReferenceById(catId).getOwnerId() == null || !catRepository.getReferenceById(catId).getOwnerId().equals(currentOwnerId))) {
            throw new Exception("Access is denied");
        }

        return catMapper.catEntityToModel(catRepository.getReferenceById(catId));
    }

    @Transactional
    public void addCat(CatModel catModel) {
        catModel.setId(catRepository.saveAndFlush(catMapper.catModelToEntity(catModel)).getCatId());
    }

    @Transactional
    public void deleteCat(long catId) throws Exception {
        List<CatEntity> catFriends = catRepository.getReferenceById(catId).getCatEntityFriends();
        for (CatEntity catEntity : catFriends){
            deleteCatFriend(catId, catEntity.getCatId(), null, null);
        }
        catRepository.deleteById(catId);
    }

    @Transactional
    public void addFriendToCat(@NonNull Long catId, @NonNull Long catFriendId, Long currentOwnerId, Role currentRole) throws Exception {

        if (currentRole != Role.ADMIN && (catRepository.getReferenceById(catId).getOwnerId() == null || !catRepository.getReferenceById(catId).getOwnerId().equals(currentOwnerId))) {
            throw new Exception("Access is denied");
        }

        CatEntity catEntity = catRepository.getReferenceById(catId);
        CatEntity friendEntity = catRepository.getReferenceById(catFriendId);

        if (catEntity.getCatEntityFriends() != null && catEntity.getCatEntityFriends().stream().anyMatch(cat -> cat.getCatId().equals(friendEntity.getCatId())))
            return;

        if (catEntity.getCatEntityFriends() == null) {
            catEntity.setCatEntityFriends(new ArrayList<>());
        }
        if (friendEntity.getCatEntityFriends() == null) {
            friendEntity.setCatEntityFriends(new ArrayList<>());
        }
        catEntity.getCatEntityFriends().add(friendEntity);
        friendEntity.getCatEntityFriends().add(catEntity);

        catRepository.saveAndFlush(catEntity);
        catRepository.saveAndFlush(friendEntity);
    }

    @Transactional
    public void deleteCatFriend(@NonNull Long catId, @NonNull Long catFriendId, Long currentOwnerId, Role currentRole) throws Exception {

        boolean flag = (currentRole == null && currentOwnerId == null);
        if (!flag){
            if (currentRole != Role.ADMIN && (catRepository.getReferenceById(catId).getOwnerId() == null || !catRepository.getReferenceById(catId).getOwnerId().equals(currentOwnerId))) {
                throw new Exception("Access is denied");
            }
        }


        CatEntity catEntity = catRepository.getReferenceById(catId);
        CatEntity friendEntity = catRepository.getReferenceById(catFriendId);

        if (catEntity.getCatEntityFriends() != null && catEntity.getCatEntityFriends().stream().anyMatch(cat -> cat.getCatId().equals(friendEntity.getCatId())))
            return;

        if (catEntity.getCatEntityFriends() == null) {
            catEntity.setCatEntityFriends(new ArrayList<>());
        }
        if (friendEntity.getCatEntityFriends() == null) {
            friendEntity.setCatEntityFriends(new ArrayList<>());
        }
        catEntity.getCatEntityFriends().add(friendEntity);
        friendEntity.getCatEntityFriends().add(catEntity);

        catRepository.saveAndFlush(catEntity);
        catRepository.saveAndFlush(friendEntity);
    }

    @Transactional
    public List<CatModel> getOwnerCats(@NonNull Long ownerId) {
        List<CatEntity> catEntities = catRepository.findAll().stream().filter(catEntity -> catEntity.getOwnerId() != null && catEntity.getOwnerId().equals(ownerId)).toList();
        return catEntities.stream().map(catEntity -> catMapper.catEntityToModel(catEntity)).toList();
    }

    @Transactional
    public List<CatModel> getCatFriends(@NonNull Long catId, Long currentOwnerId, Role currentRole) throws Exception {

        if (currentRole != Role.ADMIN && (catRepository.getReferenceById(catId).getOwnerId() == null || !catRepository.getReferenceById(catId).getOwnerId().equals(currentOwnerId))) {
            throw new Exception("Access is denied");
        }

        return catRepository.getReferenceById(catId).getCatEntityFriends().stream().map(cat -> catMapper.catEntityToModel(cat)).toList();
    }

    @Transactional
    public void addOwnerToCat(long catId, Long ownerId) {
        CatEntity catEntity = catRepository.getReferenceById(catId);
        catEntity.setOwnerId(ownerId);
        catRepository.saveAndFlush(catEntity);
    }

    @Transactional
    public List<CatModel> getByColor(@NonNull CatColor color, Long currentOwnerId, Role currentRole) {

        if (currentRole == Role.ADMIN) {
            return catRepository.getCatEntitiesByColor(color).stream().map(cat -> catMapper.catEntityToModel(cat)).toList();
        }

        return getOwnerCats(currentOwnerId).stream().filter(catModel -> catModel.getColor().equals(color)).toList();
    }


}
