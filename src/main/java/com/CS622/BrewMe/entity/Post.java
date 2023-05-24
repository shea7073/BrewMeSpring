package com.CS622.BrewMe.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    public String author;

    @Column
    public LocalDate postTime;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPostTime(LocalDate postTime) {
        this.postTime = postTime;
    }
}
