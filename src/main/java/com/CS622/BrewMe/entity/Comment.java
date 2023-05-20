package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("COM")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Comment extends Post{

    @Column
    String body;

}
