package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.entity.Post;
import com.CS622.BrewMe.entity.Review;
import com.sun.jna.platform.win32.OaIdl;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

@Transactional
public interface AleRepository extends BeerRepository<Ale> {

    //    @Query(value = "SELECT * FROM beer b WHERE UPPER(b.name) LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    @Query(value = "SELECT * FROM beer b WHERE (UPPER(b.name) LIKE CONCAT('%', :keyword, '%')) OR (UPPER(b.brewery) LIKE CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Ale> findBeers(String keyword);



}
