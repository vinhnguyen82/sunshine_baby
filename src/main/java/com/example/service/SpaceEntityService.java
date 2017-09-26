package com.example.service;

import com.example.model.SpaceEntity;
import com.example.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SpaceEntityService {

    SpaceRepository repository;

    public SpaceEntityService(SpaceRepository repository) {
        this.repository = repository;
    }


    public SpaceEntity createSpace(SpaceEntity spaceEntity) {
        return repository.save(spaceEntity);
    }

    public List<SpaceEntity> getSpaces() {
        return repository.findAll();
    }

    public SpaceEntity getSpace(int id) throws EntityNotFoundException {
        return repository.findOne(id);
    }

    public SpaceEntity updateSpace(SpaceEntity spaceEntity, int id) {
        spaceEntity.setId(id);
        return repository.save(spaceEntity);
    }
}
