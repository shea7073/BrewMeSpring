package com.CS622.BrewMe.service;

import com.CS622.BrewMe.entity.RateBeerReview;
import com.CS622.BrewMe.repository.RateBeerReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Service
public class RateBeerReviewService {

    @Autowired
    RateBeerReviewRepository rateBeerReviewRepository;

}
