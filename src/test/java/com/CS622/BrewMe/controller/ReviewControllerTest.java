package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.entity.Lager;
import com.CS622.BrewMe.entity.Review;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.LagerRepository;
import com.CS622.BrewMe.repository.ReviewRepository;
import com.CS622.BrewMe.security.SecurityConfig;
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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@Import(SecurityConfig.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private AleRepository aleRepository;
    @MockBean
    private ReviewRepository reviewRepository;
    @MockBean
    private LagerRepository lagerRepository;

    private Beer beer1;
    private List<Beer> beers = new ArrayList<>();


    private Review review1;

    @BeforeEach
    void init() {
        beer1 = new Beer();
        beer1.setName("Santilli");
        beer1.setAbv(5.2);
        beer1.setBrewery("Night Shift");
        beer1.setIbu(55);
        beer1.setAvgRating(4);
        beers.add(beer1);

        review1 = new Review();
        review1.setAuthor("sean");
        review1.setRating((float) 4.6);
        review1.setBeer(beer1);
        review1.setBody("This was very good");
        review1.setPostTime(LocalDate.now());

    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldReturnAleForm() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/beerForm/ale"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("aleForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ale"))
                .andExpect(MockMvcResultMatchers.model().attribute("ale", Matchers.instanceOf(Ale.class)));

    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldReturnLagerForm() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/beerForm/lager"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("lagerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lager"))
                .andExpect(MockMvcResultMatchers.model().attribute("lager", Matchers.instanceOf(Lager.class)));

    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldPostAleForm() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/lagerForm")
                        .param("name", "Left of the dial")
                        .param("brewery", "Notch")
                        .param("abv", String.valueOf(4.6))
                        .param("ibu", String.valueOf(55))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldPostLagerForm() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/lagerForm")
                        .param("name", "Night Lite")
                        .param("brewery", "Night Shift")
                        .param("abv", String.valueOf(4.1))
                        .param("ibu", String.valueOf(28))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldReturnBeerTypeView() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/beerType"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("beerType"));
    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldReturnSelectBeerViewAndSearchResults() throws Exception {
        doReturn(beers).when(aleRepository).findBeers(any());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/review/SelectBeer").param("keyword", "pale ale"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("reviewSelectBeer"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("beers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("keyword"))
                .andExpect(MockMvcResultMatchers.model().attribute("keyword", Matchers.containsString("pale ale")))
                .andExpect(MockMvcResultMatchers.model().attribute("beers", Matchers.hasItem(beer1)));
    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldReturnSelectBeerViewAndNoResults() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/review/SelectBeer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("reviewSelectBeer"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("beers"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("keyword"));
    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldReturnReviewFormAndIdAndNewReview() throws Exception {
        long id = 15;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/review/form/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("reviewForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("id"))
                .andExpect(MockMvcResultMatchers.model().attribute("id", Matchers.equalTo(id)))
                .andExpect(MockMvcResultMatchers.model().attributeExists("review"))
                .andExpect(MockMvcResultMatchers.model().attribute("review", Matchers.instanceOf(Review.class)));
    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldPostNewReview() throws Exception {
        long id = 15;
        Beer beer2 = new Beer();
        beer2.setId(id);
        doReturn(beer2).when(aleRepository).getReferenceById(any());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/review/form?id=" + id)
                        .param("body", "It was very good")
                        .param("rating", String.valueOf(4.5))
                        .param("id", String.valueOf(id))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    }

    @Test
    @WithMockUser(roles = "REVIEWER")
    void shouldDeleteAndRedirect() throws Exception {
        long id = 15;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/review/delete/" + id)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    }
}