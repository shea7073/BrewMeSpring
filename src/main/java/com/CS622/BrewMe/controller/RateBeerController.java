package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.RatingCalculator;
import com.CS622.BrewMe.repository.RateBeerReviewRepository;
import com.CS622.BrewMe.service.RateBeerReviewService;
import com.CS622.BrewMe.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Controller
public class RateBeerController {

    @Autowired
    private RateBeerReviewRepository rateBeerReviewRepository;

    @Autowired
    private ReadFileService readFileService;

    @GetMapping("RateBeer/Upload")
    public String uploadRateBeer(){
        readFileService.uploadRateBeer();
        return "redirect:/";
    }

    @GetMapping("/RBTest")
    public String testRB() throws ExecutionException, InterruptedException {
        List<Integer> ids = rateBeerReviewRepository.getRBIds();
        Callable<Integer> calculator = new RatingCalculator(ids, rateBeerReviewRepository);
        FutureTask<Integer> task = new FutureTask<>(calculator);
        Thread thread = new Thread(task);
        thread.start();
        //System.out.println(ids.size());
        int returned = task.get();
        System.out.println(returned);
        return "test";
    }

}
