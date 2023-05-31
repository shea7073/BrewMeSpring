package com.CS622.BrewMe.service;

import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.entity.Post;
import com.CS622.BrewMe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Service
public class UtilityService {

    @Autowired
    private ReviewRepository reviewRepository;
    public <T extends Post> long calculateAge(T t){
        // Precondition: Post passed in as argument
        // Post-condition: Post age calculated and returned
        LocalDate posted = t.getPostTime();
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(posted, today);
    }



}
