package ru.itmo.catsProject.owners.Core;

import org.springframework.stereotype.Component;
import ru.itmo.catsProject.owners.DataAccess.OwnerEntity;

@Component
public class OwnerModelEntityMapper {
    public OwnerModel ownerEntityToModel(OwnerEntity ownerEntity){
        return OwnerModel.builder()
                .name(ownerEntity.getName())
                .birthDate(ownerEntity.getBirthDate())
                .id(ownerEntity.getOwnerId())
                .build();
    }

    public OwnerEntity ownerModelToEntity(OwnerModel ownerModel){
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setName(ownerModel.getName());
        ownerEntity.setBirthDate(ownerModel.getBirthDate());

        return ownerEntity;
    }
}
