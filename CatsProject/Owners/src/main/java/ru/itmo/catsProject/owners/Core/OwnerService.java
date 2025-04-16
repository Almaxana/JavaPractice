package ru.itmo.catsProject.owners.Core;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.catsProject.common.Clients.CatClient;
import ru.itmo.catsProject.common.Dto.CatDto;
import ru.itmo.catsProject.owners.DataAccess.OwnerRepository;

import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private OwnerModelEntityMapper ownerMapper;
    @Autowired
    private CatClient catClient;

    @Transactional
    public OwnerModel getOwnerModelById(@NonNull Long id){
        return ownerMapper.ownerEntityToModel(ownerRepository.getReferenceById(id));
    }

    @Transactional
    public void addOwner(@NonNull OwnerModel ownerModel){
        ownerRepository.saveAndFlush(ownerMapper.ownerModelToEntity(ownerModel));
    }

    @Transactional
    public void deleteOwner(@NonNull Long ownerId){
        List<CatDto> ownerCats = catClient.getByOwnerId(ownerId);
        for (CatDto cat : ownerCats){
            catClient.addOwnerToCat(cat.getCatId(), null);
        }
        ownerRepository.deleteById(ownerId);
    }

}
