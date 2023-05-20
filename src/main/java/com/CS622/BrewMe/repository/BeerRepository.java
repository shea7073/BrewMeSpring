package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BeerRepository<A extends Beer> extends JpaRepository<Beer, Long> {
}
