package ru.itmo.catsProject.owners.Controller;

import org.springframework.stereotype.Component;
import ru.itmo.catsProject.common.Dto.OwnerDto;
import ru.itmo.catsProject.owners.Core.OwnerModel;

@Component
public class OwnerDtoModelMapper {
    public OwnerDto ownerModelToDto(OwnerModel ownerModel){
        return OwnerDto.builder()
                .name(ownerModel.getName())
                .birthDate(ownerModel.getBirthDate())
                .build();
    }

    public OwnerModel ownerDtoToModel(OwnerDto ownerDto){
        return OwnerModel.builder()
                .name(ownerDto.getName())
                .birthDate(ownerDto.getBirthDate())
                .build();
    }
}