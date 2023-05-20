package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.Ale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.CS622.BrewMe.repository.AleRepository;

@Controller
public class testController {
    @Autowired
    private AleRepository aleRepository;

    @GetMapping("/test")
    public String test(Model model) {
        Ale beer = new Ale();
        beer.setName("Santilli");
        beer.setBrewery("NightShift");
        beer.setAbv(5.5);
        beer.setIbu(55);
        this.aleRepository.save(beer);
        model.addAttribute("beer", beer);
        return "test";
    }
}
