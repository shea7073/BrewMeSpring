package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BRWRY")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Brewery extends Post {

    @Column
    String name;

    @Column
    String location;

    @Column
    String phoneNumber;

    // will be an image later
    @Column
    String logo;

    @Column
    int established;

}
