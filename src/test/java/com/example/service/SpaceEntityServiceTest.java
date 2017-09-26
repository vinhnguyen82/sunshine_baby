package com.example.service;

import com.example.model.SpaceEntity;
import com.example.repository.SpaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SpaceEntityServiceTest {

    @Mock
    SpaceRepository repository;

    @InjectMocks
    SpaceEntityService spaceEntityService;

    @Test
    public void createSpace_returnsSpaceEntity() {
        SpaceEntity spaceEntity = SpaceEntity.builder().build();

        when(repository.save(any(SpaceEntity.class))).thenReturn(spaceEntity);

        spaceEntityService.createSpace(spaceEntity);
        verify(repository).save(spaceEntity);
    }
}