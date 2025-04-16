package ru.itmo.catsProject.cats.Core;

import org.springframework.stereotype.Component;
import ru.itmo.catsProject.cats.DataAccess.CatEntity;

@Component
public class CatModelEntityMapper {

    public CatModel catEntityToModel(CatEntity catEntity){
        var catModelBuilder =  CatModel.builder()
                .id(catEntity.getCatId())
                .name(catEntity.getName())
                .breed(catEntity.getBreed())
                .color(catEntity.getColor())
                .birthDate(catEntity.getBirthDate());

        if (catEntity.getOwnerId() != null){
            catModelBuilder.ownerId(catEntity.getOwnerId());
        }

        if (catEntity.getCatEntityFriends() != null){
            catModelBuilder.catFriendsId(catEntity.getCatEntityFriends().stream().map(CatEntity::getCatId).toList());
        }

        return catModelBuilder.build();
    }

    public CatEntity catModelToEntity(CatModel catModel){
        CatEntity catEntity = new CatEntity();
        catEntity.setBirthDate(catModel.getBirthDate());
        catEntity.setBreed(catModel.getBreed());
        catEntity.setName( catModel.getName());
        catEntity.setColor(catModel.getColor());
        return catEntity;
    }


}
