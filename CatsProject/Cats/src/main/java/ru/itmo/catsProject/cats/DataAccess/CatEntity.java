package ru.itmo.catsProject.cats.DataAccess;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.catsProject.common.Enums.CatColor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "cats")
@NoArgsConstructor
@AllArgsConstructor
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cat_id")
    private Long catId;

    private LocalDate birthDate;

    private String breed;

    @Enumerated(EnumType.STRING)
    private CatColor color;

    private String name;

    private Long ownerId = null;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="cat_friends",
            joinColumns = {@JoinColumn(name = "cat_id", referencedColumnName = "cat_id")},
            inverseJoinColumns = {@JoinColumn(name="cat_friend_id", referencedColumnName = "cat_id")}
    )
    private List<CatEntity> catEntityFriends;
}

