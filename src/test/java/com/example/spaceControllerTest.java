package com.example;

import com.example.repository.SpaceRepository;
import org.junit.Ignore;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
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
    @Ignore
    public void createSpaceReturnsA400WhenInvalidCriteriaIsGiven() {

    }
}