package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.entity.Review;
import com.CS622.BrewMe.repository.ReviewRepository;
import com.CS622.BrewMe.security.SecurityConfig;
import com.CS622.BrewMe.service.UtilityService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@Import(SecurityConfig.class)
@WebMvcTest(HomeController.class)
    //@SpringBootTest
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private static ReviewRepository reviewRepository;

    @MockBean(name = "utilityService")
    private static UtilityService utilityService;

    private List<Review> reviews = new ArrayList<>();
    private long age = 1;

    @BeforeEach
    void init(){
        Beer beer1 = new Beer();
        beer1.setName("Santilli");
        beer1.setAbv(5.2);
        beer1.setBrewery("Night Shift");
        beer1.setIbu(55);

        Review review1 = new Review();
        review1.setAuthor("sean");
        review1.setRating((float)4.6);
        review1.setBeer(beer1);
        review1.setBody("This was very good");
        review1.setPostTime(LocalDate.now());
        reviews.add(review1);

    }
    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldReturnIndexandReviews() throws Exception {
        doReturn(reviews).when(reviewRepository).getReviews();
        doReturn(1L).when(utilityService).calculateAge(any());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("reviews"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasPrev"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasNext"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("current"))
                .andExpect(MockMvcResultMatchers.model().attribute("current", 1))
                .andExpect(MockMvcResultMatchers.model().attribute("reviews", Matchers.contains(Matchers.instanceOf(Review.class))));
    }


}