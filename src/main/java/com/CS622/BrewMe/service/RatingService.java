//package com.CS622.BrewMe.service;
//
//import com.CS622.BrewMe.entity.Review;
//import com.CS622.BrewMe.repository.AleRepository;
//import org.checkerframework.checker.units.qual.A;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RatingService {
//
//    @Autowired
//    private AleRepository aleRepository;
//
//    public float getRatingAvg(long id){
//        float total = 0;
//        List<Review> reviews = aleRepository.getReviews(id);
//
//        for (Review review : reviews) {
//            total += review.getRating();
//        }
//
//        return total / reviews.size();
//    }
//}
