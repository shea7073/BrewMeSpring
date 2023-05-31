package com.CS622.BrewMe.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String brewery;

    @Column
    private Double abv;

    @Column
    private int ibu;

    @Column
    @Nullable
    Float avgRating;

    @Column
    int numReviews;

    @Column
    LocalDate postTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public long getId() {
        return this.id;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public float getAvgRating(){
        return this.avgRating;
    }

    public int getNumReviews(){
        return this.numReviews;
    }

    public void setNumReviews(int numReviews){
        this.numReviews = numReviews;
    }

    public LocalDate getPostTime() {
        return this.postTime;
    }

    public void setPostTime(LocalDate postTime) {
        this.postTime = postTime;
    }

}
