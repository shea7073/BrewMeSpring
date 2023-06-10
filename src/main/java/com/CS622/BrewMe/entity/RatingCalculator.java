package com.CS622.BrewMe.entity;

import com.CS622.BrewMe.repository.RateBeerReviewRepository;
import java.util.List;
import java.util.concurrent.Callable;

public class RatingCalculator implements Callable<Integer> {


    private final RateBeerReviewRepository rateBeerReviewRepository;

    private List<Integer> ids;

    public RatingCalculator(List<Integer> ids, RateBeerReviewRepository rateBeerReviewRepository) {
        this.rateBeerReviewRepository = rateBeerReviewRepository;
        this.ids = ids;
    }

    public Integer call(){
        for (int id : ids) {
            double rating = 0.0;
            List<RateBeerReview> reviews = this.rateBeerReviewRepository.getRateBeerReviewByBeerId(id);
            for (RateBeerReview review : reviews){
                rating += review.getOverall();
            }
            rating /= reviews.size();
            System.out.println(rating);
        }
        return 1;
    }

}


