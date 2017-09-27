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
    SpaceRepository spaceRepository;

    @InjectMocks
    SpaceEntityService spaceEntityService;

    @Test
    public void createSpace_returnsSpaceEntity() {
        SpaceEntity spaceEntity = SpaceEntity.builder().build();

        when(spaceRepository.save(any(SpaceEntity.class))).thenReturn(spaceEntity);

        spaceEntityService.createSpace(spaceEntity);
        verify(spaceRepository).save(spaceEntity);
    }

    @Test
    public void getSpaces_returnsListOfSpaces() {
        SpaceEntity spaceEntity1 = SpaceEntity.builder().build();
        SpaceEntity spaceEntity2 = SpaceEntity.builder().build();

        when(spaceRepository.findAll()).thenReturn(Arrays.asList(spaceEntity1, spaceEntity2));

        spaceEntityService.getSpaces();
        verify(spaceRepository).findAll();
    }

    @Test
    public void getSpace_returnsTheSpaceWithTheGivenId() {
        SpaceEntity spaceEntity = SpaceEntity.builder().build();

        when(spaceRepository.findOne(anyInt())).thenReturn(spaceEntity);

        spaceEntityService.getSpace(12345);
        verify(spaceRepository).findOne(12345);
    }

    @Test
    public void updateSpace_ReturnsAnUpdatedSpace() {
        SpaceEntity spaceEntity = SpaceEntity.builder().build();

        when(spaceRepository.save(any(SpaceEntity.class))).thenReturn(spaceEntity);

        spaceEntityService.updateSpace(spaceEntity, 123);

        verify(spaceRepository).save(spaceEntity);
    }

    @Test
    public void removeSpace_RemovesASpace() {
        spaceEntityService.removeSpace(123);

        verify(spaceRepository).delete(123);
    }
}