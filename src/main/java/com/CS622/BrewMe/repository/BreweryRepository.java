package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Brewery;
import jakarta.transaction.Transactional;

@Transactional
public interface BreweryRepository extends PostRepository<Brewery> {
}
