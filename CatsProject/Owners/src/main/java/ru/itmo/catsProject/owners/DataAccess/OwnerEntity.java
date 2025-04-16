package ru.itmo.catsProject.owners.DataAccess;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="owner_id")
    private Long ownerId;

    private String name;

    private LocalDate birthDate;
}
