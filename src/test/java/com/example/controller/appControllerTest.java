package com.example.controller;

import com.example.model.AppEntity;
import com.example.model.SpaceEntity;
import com.example.repository.AppRepository;
import com.example.repository.SpaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class appControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private AppRepository appRepository;

    @Test
    public void createAppReturnsANewAppInTheGivenSpace() throws Exception {
        SpaceEntity spaceEntity = SpaceEntity.builder()
                .name("Chad")
                .disk(1024)
                .memory(512)
                .build();

        spaceRepository.save(spaceEntity);

        int spaceId = spaceEntity.getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/spaces/" + spaceId + "/apps")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"bob\",\"diskAlloc\":120,\"memoryAlloc\":10}");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("bob")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.diskAlloc", is(120)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.memoryAlloc", is(10)));

        assertThat(appRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void getAllAppsReturnsAllAppsWithinAGivenSpaceId() throws Exception {
        SpaceEntity spaceEntity = SpaceEntity.builder()
                .name("Chad")
                .disk(1024)
                .memory(512)
                .build();

        SpaceEntity spaceEntity2 = SpaceEntity.builder()
                .name("space2")
                .disk(1024)
                .memory(512)
                .build();

        spaceRepository.save(Arrays.asList(spaceEntity, spaceEntity2));

        AppEntity appEntity1 = AppEntity.builder()
                .name("app1")
                .diskAlloc(10)
                .memoryAlloc(1)
                .spaceId(spaceEntity.getId())
                .build();

        AppEntity appEntity2 = AppEntity.builder()
                .name("app2")
                .diskAlloc(15)
                .memoryAlloc(1)
                .spaceId(spaceEntity2.getId())
                .build();

        AppEntity appEntity3 = AppEntity.builder()
                .name("app3")
                .diskAlloc(20)
                .memoryAlloc(1)
                .spaceId(spaceEntity.getId())
                .build();

        appRepository.save(appEntity1);
        appRepository.save(appEntity2);
        appRepository.save(appEntity3);

        int spaceId = spaceEntity.getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/spaces/" + spaceId + "/apps")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("app1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("app3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].diskAlloc", is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].diskAlloc", is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].spaceId", is(spaceEntity.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].spaceId", is(spaceEntity.getId())));
    }
}