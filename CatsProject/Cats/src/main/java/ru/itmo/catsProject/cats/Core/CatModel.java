package ru.itmo.catsProject.cats.Core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.catsProject.common.Enums.CatColor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CatModel {
    @Builder.Default
    private Long id = null;
    private String name;
    private String breed;
    private CatColor color;
    private Long ownerId;
    private LocalDate birthDate;
    @Builder.Default
    private List<Long> catFriendsId = null;
}
