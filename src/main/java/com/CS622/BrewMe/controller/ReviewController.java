package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.entity.Lager;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.BeerRepository;
import com.CS622.BrewMe.repository.LagerRepository;
import com.CS622.BrewMe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class ReviewController {

    @Autowired
    private AleRepository aleRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private LagerRepository lagerRepository;



    @GetMapping("/beerType")
    public String beerType() {
        return "beerType";
    }

    @GetMapping("/aleForm")
    public String aleForm(Principal principle, Model model) {
        model.addAttribute("ale", new Ale());
        return "aleForm";
    }

    @PostMapping("/aleForm")
    public String submitAle(@ModelAttribute("ale") Ale ale) {
        aleRepository.save(ale);
        return "index";
    }

    @GetMapping("/review/SelectBeer")
    public String reviewSelect(Model model, @Param("keyword") String keyword){
        if (keyword != null && keyword != ""){
            List<Ale> aleList = aleRepository.findBeers(keyword);
            model.addAttribute("beers", aleList);
        }
        model.addAttribute("keyword", keyword);
        return "reviewSelectBeer";
    }

}
