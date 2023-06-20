package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.RateBeerReview;
import com.CS622.BrewMe.entity.RatingCalculator;
import com.CS622.BrewMe.repository.RateBeerReviewRepository;
import com.CS622.BrewMe.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
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
        // PRECONDITION: Admin navigates to the RateBeer/Upload endpoint
        // POSTCONDITION: All review data from the ratebeer repository is loaded in the DB
        readFileService.uploadRateBeer();
        return "redirect:/";
    }

    @GetMapping("/RateBeer")
    public String rateBeer(Model model) throws ExecutionException, InterruptedException {

        // PRECONDITION: Reviewer navigates to /RateBeer endpoint
        // POSTCONDITION: Threads are used to calculate the average ratings of all
        // beers in the ratebeer upload table. Only those scoring higher than 15/20
        // are returned to the template

        // Get every unique beer id in the table
        List<Integer> ids = rateBeerReviewRepository.getRBIds();

        // Get the score from every review along with the reviews associated beer id
        List<Object[]> ratings = rateBeerReviewRepository.getRatingData();

        // Split the IDs into 4 equal size parts
        List<Integer> chunk1 = ids.subList(0, ids.size()/4);
        List<Integer> chunk2 = ids.subList(ids.size()/4, ids.size()/2);
        List<Integer> chunk3 = ids.subList(ids.size()/2, (ids.size()/4)*3);
        List<Integer> chunk4 = ids.subList((ids.size()/4)*3, ids.size());

        // Create 4 RatingCalculator Callables and pass a chunk of IDs to each
        Callable<Map<Integer, Long>> calculator1 = new RatingCalculator(chunk1, ratings);
        Callable<Map<Integer, Long>> calculator2 = new RatingCalculator(chunk2, ratings);
        Callable<Map<Integer, Long>> calculator3 = new RatingCalculator(chunk3, ratings);
        Callable<Map<Integer, Long>> calculator4 = new RatingCalculator(chunk4, ratings);

        // Create a FutureTask for each RatingCalculator Callable
        FutureTask<Map<Integer, Long>> task1 = new FutureTask<>(calculator1);
        FutureTask<Map<Integer, Long>> task2 = new FutureTask<>(calculator2);
        FutureTask<Map<Integer, Long>> task3 = new FutureTask<>(calculator3);
        FutureTask<Map<Integer, Long>> task4 = new FutureTask<>(calculator4);

        // Create a thread for each task
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        Thread thread4 = new Thread(task4);

        // Start the threads
        thread1.start();
        System.out.println("Thread 1 Started");
        thread2.start();
        System.out.println("Thread 2 Started");
        thread3.start();
        System.out.println("Thread 3 Started");
        thread4.start();
        System.out.println("Thread 4 Started");

        // Collect results from each thread
        Map<Integer, Long> returned1 = task1.get();
        System.out.println("Thread 1 Ended");
        Map<Integer, Long> returned2 = task2.get();
        System.out.println("Thread 2 Ended");
        Map<Integer, Long> returned3 = task3.get();
        System.out.println("Thread 3 Ended");
        Map<Integer, Long> returned4 = task4.get();
        System.out.println("Thread 4 Ended");

        // Compile the results into a single hashmap
        HashMap<Integer, Long> finalMap = new HashMap<>();
        finalMap.putAll(returned1);
        finalMap.putAll(returned2);
        finalMap.putAll(returned3);
        finalMap.putAll(returned4);

        // Container for data to be sent to the template
        List<Object[]> templateData = new ArrayList<>();

        for (Map.Entry<Integer,Long> entry : finalMap.entrySet()){
            // Get a review object by id
            RateBeerReview review = rateBeerReviewRepository.get1ReviewById(entry.getKey());

            // Clean the name data
            String name = review.getName();
            name = name.replaceAll("\\p{Punct}", "");
            name = name.replaceAll("�", "");
            name = name.replaceAll("40", "");
            name = name.replaceAll("41", "");
            review.setName(name);

            // Clean the style data
            String style = review.getStyle();
            style = style.replaceAll("\\p{Punct}", "");
            style = style.replaceAll("�", "");
            style = style.replaceAll("40", "");
            style = style.replaceAll("41", "");
            review.setStyle(style);

            // Create array with the review and the calculated average rating
            Object[] data = {review, entry.getValue()};
            // Add the array to template data
            templateData.add(data);

        }
        // Sort the template data but average rating
        templateData.sort((o1, o2) -> {
            if ((Long)o1[1] < (Long)o2[1]) {
                return 1;
            } else if (o1[1] == o2[1]) {
                return 0;
            } else {
                return -1;
            }
        });

        // Add template data to the model
        model.addAttribute("topBeers", templateData);

        // Return rateBeerReviews.html
        return "rateBeerReviews";
    }

}
