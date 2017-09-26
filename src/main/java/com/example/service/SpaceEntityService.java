package com.example.service;

import com.example.model.SpaceEntity;
import com.example.repository.SpaceRepository;
import org.springframework.stereotype.Service;

@Service
public class SpaceEntityService {

    SpaceRepository repository;

    public SpaceEntityService(SpaceRepository repository) {
        this.repository = repository;
    }


    public SpaceEntity createSpace(SpaceEntity spaceEntity) {
        return repository.save(spaceEntity);
    }
}
