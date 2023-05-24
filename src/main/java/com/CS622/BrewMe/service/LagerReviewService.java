package com.CS622.BrewMe.service;

import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.entity.Lager;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.BeerRepository;
import com.CS622.BrewMe.repository.LagerRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LagerReviewService {

    private LagerRepository lagerRepository;

    public LagerReviewService(LagerRepository lagerRepository){
        this.lagerRepository = lagerRepository;
    }

    public List<Lager> findBeers(String keyword) {
        List<Lager> lagers = lagerRepository.findBeers(keyword);
        return lagers;
    }

}