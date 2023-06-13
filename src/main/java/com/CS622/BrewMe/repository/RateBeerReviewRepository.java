package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.entity.RateBeerReview;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Objects;

public interface RateBeerReviewRepository extends JpaRepository<RateBeerReview, Long> {


    @Query(value = "SELECT DISTINCT(beer_id) FROM rate_beer_review", nativeQuery = true)
    List<Integer> getRBIds();

    @Query(value = "SELECT overall FROM rate_beer_review WHERE beer_id = :id", nativeQuery = true)
    List<Double> getRateBeerReviewByBeerId(int id);

    @Query(value = "SELECT overall, beer_id FROM rate_beer_review", nativeQuery = true)
    List<Object[]> getRatingData();

    @Query(value = "SELECT * FROM rate_beer_review WHERE beer_id = :id LIMIT 1", nativeQuery = true)
    RateBeerReview get1ReviewById(int id);
}
