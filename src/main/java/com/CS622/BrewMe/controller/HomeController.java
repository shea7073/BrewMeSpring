package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.Review;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.ReviewRepository;
import com.CS622.BrewMe.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String home(Model model){
        List<Review> reviews = reviewRepository.getReviews();
        model.addAttribute("reviews", reviews);
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
