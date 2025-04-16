package ru.itmo.catsProject.cats.Controller;

import org.springframework.stereotype.Component;
import ru.itmo.catsProject.cats.Core.CatModel;
import ru.itmo.catsProject.common.Dto.CatDto;

@Component
public class CatDtoModelMapper {
    public CatModel catDtoToModel(CatDto catDto){
        return CatModel.builder()
                .id(catDto.getCatId())
                .breed(catDto.getBreed())
                .color(catDto.getColor())
                .birthDate(catDto.getBirthDate())
                .name(catDto.getName())
                .catFriendsId(catDto.getCatFriendsId())
                .ownerId(catDto.getOwnerId())
                .build();
    }

    public CatDto catModelToDto(CatModel catModel){
        return CatDto.builder()
                .catId(catModel.getId())
                .breed(catModel.getBreed())
                .color(catModel.getColor())
                .birthDate(catModel.getBirthDate())
                .name(catModel.getName())
                .catFriendsId(catModel.getCatFriendsId())
                .ownerId(catModel.getOwnerId())
                .build();
    }
}
