package com.CS622.BrewMe.entity;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class RatingCalculator implements Callable<Map<Integer, Long>> {

    private List<Integer> ids;
    private List<Object[]> ratingData;

    public RatingCalculator(List<Integer> ids, List<Object[]> ratings) {
        // Setup needed for call method
        this.ids = ids;
        this.ratingData = ratings;
    }

    public Map<Integer, Long> call(){
        // Will hold beer ID and average rating for each beer
        HashMap<Integer, Long> ratings = new HashMap<>();

        // Loop through all ids that were passed to the class
        for (int id : ids) {
            // Keeps track of # of reviews
            int numReviews = 0;
            // Local variable for holding the rating
            double rating = 0.0;
            // Loop through all the rating data
            for (Object[] review : this.ratingData){
                // If the id for the current loop matches the id on the review
                // add the rating to the talley and increment # of reviews
                if ((Integer) review[1] == id) {
                    rating += (Double) review[0];
                    numReviews += 1;
                }
            }
            // Average rating by dividing by the # of reviews
            rating /= numReviews;
            // Multiply by 20 to bring the review back to x/20
            rating *= 20;
            // Add the final rating and beer ID to the map
            ratings.put(id, (long) rating);
        }
        // This filters out ratings below 15/20 (So we only get bestsellers)
        Map<Integer, Long> result = ratings.entrySet().stream().filter(map -> map.getValue() > 15)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Return hashmap with all the IDs and their calculated average rating
        return result;
    }

}


