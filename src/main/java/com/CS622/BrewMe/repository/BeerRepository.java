package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface BeerRepository<A extends Beer> extends JpaRepository<Beer, Long> {


}
