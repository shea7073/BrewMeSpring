package com.CS622.BrewMe.service;

import com.CS622.BrewMe.entity.Ale;
import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.repository.BeerRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AleReviewService {

    private AleRepository aleRepository;

    public AleReviewService(AleRepository aleRepository){
        this.aleRepository = aleRepository;
    }

    public List<Ale> findBeers(String keyword) {
        List<Ale> ales = aleRepository.findBeers(keyword);
        return ales;
    }

}
