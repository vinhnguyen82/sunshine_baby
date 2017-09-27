package com.example.service;

import com.example.model.AppEntity;
import com.example.repository.AppRepository;
import org.springframework.stereotype.Service;

@Service
public class AppEntityService {

    AppRepository appRepository;

    public AppEntityService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public AppEntity createApp(AppEntity appEntity, int spaceId) {

        appEntity.setSpaceId(spaceId);

        return appRepository.save(appEntity);
    }
}
