package ru.itmo.catsProject.cats.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.catsProject.common.Enums.CatColor;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, Long> {
    List<CatEntity> getCatEntitiesByColor(CatColor color);

}
