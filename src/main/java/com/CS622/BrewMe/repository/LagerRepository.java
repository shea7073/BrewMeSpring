package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Ale;
import jakarta.transaction.Transactional;
import com.CS622.BrewMe.entity.Lager;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface LagerRepository extends BeerRepository<Lager>{

    @Query(value = "SELECT * FROM beer b WHERE UPPER(b.name) LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    public List<Lager> findBeers(String keyword);



}
