package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.PaginatedList;
import com.CS622.BrewMe.entity.Review;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.ReviewRepository;
import com.CS622.BrewMe.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AleRepository aleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UtilityService utilityService;

    @GetMapping("/")
    public String home(Model model, @Param("page") Integer page){
        // Precondition: Visit to site "/" route
        // Post condition: Return index template with reviews
        List<Review> reviews = reviewRepository.getReviews();
        // GENERIC USAGE
        PaginatedList<Review> paginatedReviews = new PaginatedList<>(reviews, 1);
        if (page != null) {
            paginatedReviews.setCurrentPageNum(page);
        }
        else {
            paginatedReviews.setCurrentPageNum(1);
        }
        int current = paginatedReviews.getCurrentPageNum();
        boolean hasNext = paginatedReviews.hasNext();
        boolean hasPrev = paginatedReviews.hasPrevious();
        model.addAttribute("reviews", paginatedReviews.getCurrentPageItems());
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("current", current);
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/brewer")
    public String brewer() {
        return "brewer";
    }


}
