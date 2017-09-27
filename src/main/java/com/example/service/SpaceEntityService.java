package com.example.service;

import com.example.model.SpaceEntity;
import com.example.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SpaceEntityService {

    SpaceRepository spaceRepository;

    public SpaceEntityService(SpaceRepository repository) {
        this.spaceRepository = repository;
    }


    public SpaceEntity createSpace(SpaceEntity spaceEntity) {
        return spaceRepository.save(spaceEntity);
    }

    public List<SpaceEntity> getSpaces() {
        return spaceRepository.findAll();
    }

    public SpaceEntity getSpace(int id) throws EntityNotFoundException {
        return spaceRepository.findOne(id);
    }

    public SpaceEntity updateSpace(SpaceEntity spaceEntity, int id) {
        spaceEntity.setId(id);
        return spaceRepository.save(spaceEntity);
    }

    public void removeSpace(int id) {
        spaceRepository.delete(id);
    }
}
