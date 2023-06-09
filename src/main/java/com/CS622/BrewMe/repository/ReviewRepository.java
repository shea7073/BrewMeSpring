package com.CS622.BrewMe.repository;


import com.CS622.BrewMe.entity.Post;
import com.CS622.BrewMe.entity.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface ReviewRepository extends PostRepository<Review>{

    @Query(value = "SELECT * from post p WHERE p.type = 'REVIEW' ORDER BY post_time DESC", nativeQuery = true)
    List<Review> getReviews();

    @Query(value = "SELECT * FROM post p WHERE p.id = :id AND p.type = 'REVIEW'", nativeQuery = true)
    Review getReview(long id);

}
