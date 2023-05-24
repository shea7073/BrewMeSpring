package com.CS622.BrewMe.controller;

//import com.CS622.BrewMe.entity.User;
import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Review;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class UserController {

    @Autowired
    private AleRepository aleRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String home(Principal principal){

//        Ale beer = new Ale();
//        beer.setName("Santilli");
//        beer.setIbu(55);
//        beer.setBrewery("Night Shift");
//        beer.setAbv(5.5);
//        aleRepository.save(beer);
//        Review review = new Review();
//        review.setAuthor(principal.getName());
//        review.setPostTime(LocalDate.now());
//        review.setBeer(beer);
//        review.setBody("It was good");
//        review.setRating((float) 4.5);
//        review.setImage("dummy");
//        reviewRepository.save(review);

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
