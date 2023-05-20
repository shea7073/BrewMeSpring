package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("REVIEW")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Review extends Post {

    @OneToOne(cascade = CascadeType.ALL)
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

}
