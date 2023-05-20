package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Admin extends User{



}
