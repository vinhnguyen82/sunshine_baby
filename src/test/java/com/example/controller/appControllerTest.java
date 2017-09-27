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

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/spaces/" + spaceId + "/apps" )
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"bob\",\"diskAlloc\":120,\"memoryAlloc\":10}");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("bob")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.diskAlloc", is(120)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.memoryAlloc", is(10)));

        assertThat(appRepository.findAll().size()).isEqualTo(1);
    }

}