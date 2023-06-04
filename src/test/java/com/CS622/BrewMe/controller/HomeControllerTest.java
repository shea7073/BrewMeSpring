package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.Review;
import com.CS622.BrewMe.repository.ReviewRepository;
import com.CS622.BrewMe.security.SecurityConfig;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;
import java.util.List;

@Import(SecurityConfig.class)
@WebMvcTest(HomeController.class)
    //@SpringBootTest
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldWork() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("reviews"))
                .andExpect(MockMvcResultMatchers.model().attribute("reviews", Matchers.contains(List.of(Review.class))));
    }


}