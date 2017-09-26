package com.example;

import com.example.model.SpaceEntity;
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
import static org.hamcrest.Matchers.isEmptyOrNullString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class spaceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SpaceRepository spaceRepository;


    @Test
    public void createSpaceReturnsANewSpace() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/spaces")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"bob\",\"disk\":120,\"memory\":13}");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("bob")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disk", is(120)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.memory", is(13)));

        assertThat(spaceRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void createSpaceReturnsA400WhenBadRequestIsMade() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/spaces")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        assertThat(spaceRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    public void getSpacesReturnsAListOfSpaces() throws Exception {
        SpaceEntity space1 = SpaceEntity.builder()
                .name("bob")
                .disk(120)
                .build();
        SpaceEntity space2 = SpaceEntity.builder()
                .name("leila")
                .disk(100)
                .build();

        spaceRepository.save(Arrays.asList(space1, space2));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/spaces")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("bob")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].disk", is(120)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("leila")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].disk", is(100)));
    }

    @Test
    public void getSpaceReturnsASpaceWhenItReceivesAnIdOfASpaceEntity() throws Exception {
        SpaceEntity space = SpaceEntity.builder()
                .name("bob")
                .disk(120)
                .memory(10)
                .build();

        int spaceId = spaceRepository.save(space).getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/spaces/" + spaceId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("bob")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disk", is(120)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.memory", is(10)));
    }

    @Test
    public void updateSpaceReturnsAnUpdatedSpace() throws Exception {
        SpaceEntity newSpace = SpaceEntity.builder()
                .name("bob")
                .disk(120)
                .memory(10)
                .build();

        int spaceId = spaceRepository.save(newSpace).getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch("/spaces/" + spaceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"abstract\",\"disk\":10,\"memory\":10}");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("abstract")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disk", is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.memory", is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(spaceId)));

        assertThat(spaceRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void removeSpaceDeletesTheSpaceWithTheGivenId() throws Exception {
        SpaceEntity newSpace = SpaceEntity.builder()
                .name("bob")
                .disk(120)
                .memory(10)
                .build();

        int spaceId = spaceRepository.save(newSpace).getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/spaces/" + spaceId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        assertThat(spaceRepository.findAll().size()).isEqualTo(0);
    }
}