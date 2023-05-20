package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Ale;
import jakarta.transaction.Transactional;

@Transactional
public interface AleRepository extends BeerRepository<Ale>{
}
