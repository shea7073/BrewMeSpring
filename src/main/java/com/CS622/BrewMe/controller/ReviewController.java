package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.*;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.LagerRepository;
import com.CS622.BrewMe.repository.ReviewRepository;

import com.CS622.BrewMe.service.UtilityService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class ReviewController {

    @Autowired
    private AleRepository aleRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private LagerRepository lagerRepository;

    @Autowired
    private UtilityService utilityService;


    @GetMapping("/beerType")
    public String beerType() {
        return "beerType";
    }

    @GetMapping("/beerForm/{type}")
    public String beerForm(Model model, @PathVariable("type") String type) {
        if (Objects.equals(type, "ale")){
            model.addAttribute("ale", new Ale());
            return "aleForm";

        }
        if (Objects.equals(type, "lager")){
            model.addAttribute("lager", new Lager());
            System.out.println("lager creeated");
            return "lagerForm";
        }

        return "index";
    }

    @PostMapping("/aleForm")
    public String submitAle(@ModelAttribute("ale") Ale ale) {
        // this needs to check if there is a beer with that name in the system already
        ale.setNumReviews(0);
        ale.setAvgRating(0);
        aleRepository.save(ale);
        return "redirect:/";
    }

    @PostMapping("/lagerForm")
    public String submitLager(@ModelAttribute("lager") Lager lager) {
        lager.setAvgRating(0);
        lager.setNumReviews(0);
        lagerRepository.save(lager);
        return "redirect:/";
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

    @GetMapping("/review/form/{id}")
    public String reviewForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("review", new Review());
        model.addAttribute("id", id);
        System.out.println(aleRepository.getReferenceById(id));
        return "reviewForm";
    }

    @PostMapping("/review/form")
    public String submitReview(@ModelAttribute("review") Review review, Principal principal, @RequestParam("id") long id) {
        review.setPostTime(LocalDate.now());
        review.setAuthor(principal.getName());
        review.setBeer(aleRepository.getReferenceById(id));
        reviewRepository.save(review);
        Beer beer = aleRepository.getReferenceById(id);
        if (beer.getNumReviews() == 0){
            beer.setAvgRating(review.getRating());
            beer.setNumReviews(1);
            aleRepository.save(beer);
        }
        else {
            float data = beer.getAvgRating() * beer.getNumReviews();
            beer.setNumReviews(beer.getNumReviews() + 1);
            data += review.getRating();
            float finalReview = data / beer.getNumReviews();
            beer.setAvgRating(finalReview);
            aleRepository.save(beer);
        }

        return "redirect:/";
    }

    @PostMapping("review/delete/{id}")
    public String deleteReview(@PathVariable("id") long id){
        reviewRepository.deleteById(id);
        return "redirect:/";
    }

}
