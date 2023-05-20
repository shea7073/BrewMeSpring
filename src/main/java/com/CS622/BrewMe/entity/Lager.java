package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LAGER")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Lager extends Beer {

    @Column
    String fermentation = "Bottom-Fermented";

    @Column
    String yeast = "Saccharomyces pastorianus";

}

