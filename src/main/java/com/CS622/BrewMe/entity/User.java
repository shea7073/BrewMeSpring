//package com.CS622.BrewMe.entity;
//
//
//import jakarta.persistence.*;
//import org.checkerframework.checker.units.qual.C;
//
//import java.time.LocalDate;
//
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//public abstract class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    @Column(name="name")
//    String name;
//
//    @Column(name="username")
//    String username;
//
//    @Column
//    LocalDate postTime;
//
//
//    public String getName() {
//        return name;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public LocalDate getPostTime() {
//        return this.postTime;
//    }
//
//    public void setPostTime(LocalDate postTime) {
//        this.postTime = postTime;
//    }
//}
