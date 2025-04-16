package ru.itmo.catsProject.common.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.catsProject.common.Enums.CatColor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {
    private Long catId;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private CatColor color;
    private Long ownerId;
    @Builder.Default
    private List<Long> catFriendsId = null;
}
