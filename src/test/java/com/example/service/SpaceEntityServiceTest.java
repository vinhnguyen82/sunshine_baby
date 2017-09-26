package com.example.service;

import com.example.model.SpaceEntity;
import com.example.repository.SpaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
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

    @Test
    public void getSpaces_returnsListOfSpaces() {
        SpaceEntity spaceEntity1 = SpaceEntity.builder().build();
        SpaceEntity spaceEntity2 = SpaceEntity.builder().build();

        when(repository.findAll()).thenReturn(Arrays.asList(spaceEntity1, spaceEntity2));

        spaceEntityService.getSpaces();
        verify(repository).findAll();
    }

    @Test
    public void getSpace_returnsTheSpaceWithTheGivenId() {
        SpaceEntity spaceEntity = SpaceEntity.builder().build();

        when(repository.findOne(anyInt())).thenReturn(spaceEntity);

        spaceEntityService.getSpace(12345);
        verify(repository).findOne(12345);
    }

}