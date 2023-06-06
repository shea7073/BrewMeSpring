package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.*;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.LagerRepository;
import com.CS622.BrewMe.repository.ReviewRepository;

import com.CS622.BrewMe.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ReviewController {

    @Autowired
    private AleRepository aleRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private LagerRepository lagerRepository;




    @GetMapping("/beerType")
    // Precondition: Visit to site "/beerType" route
    // Post condition: Return beerType template so user can choose
    // whether to submit an ale or lager.
    public String beerType() {
        return "beerType";
    }

    @GetMapping("/beerForm/{type}")
    // Precondition: User selects Ale or Lager on "/beerType" route
    // Post condition: If ale chosen, return form for ale creation. If lager
    // chosen return the lager form.
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
        // catch all if route doesn't match
        return "index";
    }

    @PostMapping("/aleForm")
    public String submitAle(@ModelAttribute("ale") Ale ale) {
        // Precondition: User has posted an ale form
        // Post condition: Ale object gets initial values and is saved to mysql

        // FUTURE SEAN: this needs to check if there is a beer with that name in the system already
        ale.setNumReviews(0);
        ale.setAvgRating(0);
        aleRepository.save(ale);
        return "redirect:/";
    }

    @PostMapping("/lagerForm")
    public String submitLager(@ModelAttribute("lager") Lager lager) {
        // Precondition: User has posted a lager form
        // Post condition: Lager object gets initial values and is saved to mysql
        lager.setAvgRating(0);
        lager.setNumReviews(0);
        lagerRepository.save(lager);
        return "redirect:/";
    }

    @GetMapping("/review/SelectBeer")
    public String reviewSelect(Model model, @Param("keyword") String keyword){
        // Precondition: User navigates to "/review/SelectBeer" route
        // Post condition: Review select beer template is displayed. If a keyword
        // is provided, also return search results
        if (keyword != null && keyword != ""){
            List<Ale> aleList = aleRepository.findBeers(keyword);
            // GENERIC USAGE
            PaginatedList<Ale> paginatedBeers = new PaginatedList<>(aleList, 1);
            model.addAttribute("beers", paginatedBeers.getCurrentPageItems());
        }
        model.addAttribute("keyword", keyword);
        return "reviewSelectBeer";
    }

    @GetMapping("/review/form/{id}")
    public String reviewForm(Model model, @PathVariable("id") long id) {
        // Precondition: User navigates to "/review/form/{id}" route to submit a review
        // Post condition: New review object is generated and form is presented to user for completion
        model.addAttribute("review", new Review());
        model.addAttribute("id", id);
        System.out.println(aleRepository.getReferenceById(id));
        return "reviewForm";
    }

    @PostMapping("/review/form")
    public String submitReview(@ModelAttribute("review") Review review, Principal principal, @RequestParam("id") long id) {
        // Precondition: User posts review form
        // Post condition: Review is finished and saved. Beer has its number of reviews and rating updated.
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
    public String deleteReview(@PathVariable("id") long id, Principal principal){
        // Precondition: User has selected to delete their post
        // Postcondition: Review is deleted, user redirected home
        Review review = reviewRepository.getReview(id);
        if (Objects.equals(review.getAuthor(), principal.getName())) {
            reviewRepository.deleteById(id);
        }
        return "redirect:/";
    }

}
