package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Reviewer;
import jakarta.transaction.Transactional;

@Transactional
public interface ReviewerRepository extends UserRepository<Reviewer>{
}
