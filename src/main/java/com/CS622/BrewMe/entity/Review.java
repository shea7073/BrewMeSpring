package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("REVIEW")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Review extends Post {

    @OneToOne
    Beer beer;

    @Column
    String body;

    @Column
    float rating;

//    @Lob
//    @Column(length = 1000)
//    private byte[] image;

    // will be an image later
    @Column
    String image;


    @OneToOne
    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
