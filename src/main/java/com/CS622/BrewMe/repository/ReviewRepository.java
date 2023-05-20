package com.CS622.BrewMe.repository;


import com.CS622.BrewMe.entity.Review;
import jakarta.transaction.Transactional;

@Transactional
public interface ReviewRepository extends PostRepository<Review>{
}
