package ru.itmo.catsProject.owners.Core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OwnerModel {
    @Builder.Default
    private Long id = null;
    private String name;
    private LocalDate birthDate;
}

