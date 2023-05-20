package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Brewer;
import jakarta.transaction.Transactional;

@Transactional
public interface BrewerRepository extends UserRepository<Brewer>{
}
