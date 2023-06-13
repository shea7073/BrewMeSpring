package com.CS622.BrewMe.service;

import com.CS622.BrewMe.entity.RateBeerReview;
import com.CS622.BrewMe.repository.RateBeerReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Scanner;

@Service
public class ReadFileService {

    @Autowired
    RateBeerReviewRepository rateBeerReviewRepository;

    public void uploadRateBeer() {

        FileInputStream inputStream = null;
        Scanner scanner;
        try {
            // Create stream for file
            inputStream = new FileInputStream("/Users/seanshea/Downloads/ratebeer.txt");
        } catch (Exception e) {
            System.out.println(e);
        }
        // Create scanner with stream
        scanner = new Scanner(inputStream, "UTF-8");
        // Create a review object
        RateBeerReview rateBeerReview = new RateBeerReview();
        // While file has more lines
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // If line is empty save review and reset review variable
            if (line.isEmpty()) {
                System.out.println(rateBeerReview.getName());
                rateBeerReviewRepository.save(rateBeerReview);
                rateBeerReview = new RateBeerReview();
            // Parse the line and set values on the review object
            } else {
                if (line.startsWith("beer/name:")) {
                    rateBeerReview.setName(line.split(": ")[1]);
                } else if (line.startsWith("beer/beerId:")) {
                    rateBeerReview.setBeerId(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("beer/brewerId:")) {
                    rateBeerReview.setBrewerId(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("beer/ABV:")) {
                    if (Objects.equals(line.split(": ")[1], "-")){
                        rateBeerReview.setAbv(0D);
                    }
                    else {
                        rateBeerReview.setAbv(Double.parseDouble(line.split(": ")[1]));
                    }
                } else if (line.startsWith("beer/style:")) {
                    rateBeerReview.setStyle(line.split(": ")[1]);
                } else if (line.startsWith("review/appearance:")) {
                    String strValue = line.split(": ")[1];
                    Double dblValue = Double.parseDouble(String.valueOf(strValue.charAt(0))) / 5;
                    rateBeerReview.setAppearance(dblValue);
                } else if (line.startsWith("review/aroma:")) {
                    String strValue = line.split(": ")[1];
                    String numerator = strValue.split("/")[0];
                    Double dblValue = Double.parseDouble(numerator) / 10;
                    rateBeerReview.setAroma(dblValue);
                } else if (line.startsWith("review/palate:")) {
                    String strValue = line.split(": ")[1];
                    Double dblValue = Double.parseDouble(String.valueOf(strValue.charAt(0))) / 5;
                    rateBeerReview.setPalate(dblValue);
                } else if (line.startsWith("review/taste:")) {
                    String strValue = line.split(": ")[1];
                    String numerator = strValue.split("/")[0];
                    Double dblValue = Double.parseDouble(numerator) / 10;
                    rateBeerReview.setTaste(dblValue);
                } else if (line.startsWith("review/overall:")) {
                    String strValue = line.split(": ")[1];
                    String numerator = strValue.split("/")[0];
                    Double dblValue = Double.parseDouble(numerator) / 20;
                    rateBeerReview.setOverall(dblValue);
                } else if (line.startsWith("review/time:")) {
                    rateBeerReview.setTime(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("review/profileName:")) {
                    rateBeerReview.setProfileName(line.split(": ")[1]);
                } else if (line.startsWith("review/text:")) {
                    System.out.println(line);
                    if (line.split(": ").length > 1){
                        rateBeerReview.setText(line.split(": ")[1]);
                    }
                    else {
                        rateBeerReview.setText("None");
                    }
                }
            }
        }
    }



}