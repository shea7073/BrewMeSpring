package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Beer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface AleRepository extends BeerRepository<Ale>{

    @Query(value = "SELECT * FROM beer b WHERE UPPER(b.name) LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    public List<Ale> findBeers(String keyword);

}
