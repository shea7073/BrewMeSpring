package com.CS622.BrewMe.repository;

import jakarta.transaction.Transactional;
import com.CS622.BrewMe.entity.Lager;

@Transactional
public interface LagerRepository extends BeerRepository<Lager>{
}
