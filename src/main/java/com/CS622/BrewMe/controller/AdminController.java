package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.manualSQL.averageReviewRating;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/adminPanel")
    public String adminPanel(){
        return "adminPanel";
    }

    @GetMapping("/ratingStats")
    public String ratingStats(Model model) throws SQLException {

        List<Object[]> templateData = new ArrayList<>();
        ArrayList<Object[]> data = averageReviewRating.ratingsByAuthor();
        model.addAttribute("data", data);
        return "ratingStats";
    }

}
