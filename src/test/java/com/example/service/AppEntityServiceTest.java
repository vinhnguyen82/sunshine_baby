package com.example.service;


import com.example.model.AppEntity;
import com.example.model.SpaceEntity;
import com.example.repository.AppRepository;
import com.example.repository.SpaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AppEntityServiceTest {

    @Mock
    AppRepository appRepository;

    @InjectMocks
    AppEntityService appEntityService;

    @Test
    public void createApp_ReturnsAppEntityWithInTheGivenSpaceId() {

        SpaceEntity spaceEntity = SpaceEntity.builder()
                .id(222)
                .name("bb")
                .build();

        AppEntity appEntity = AppEntity.builder()
                .name("test")
                .diskAlloc(100)
                .memoryAlloc(10)
                .build();

        int spaceId = spaceEntity.getId();

        when(appRepository.save(any(AppEntity.class))).thenReturn(appEntity);

        appEntityService.createApp(appEntity, spaceId);

        verify(appRepository).save(appEntity);
    }
}