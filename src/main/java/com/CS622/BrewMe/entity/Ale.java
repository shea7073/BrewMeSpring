package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ALE")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Ale extends Beer {

    @Column
    String fermentation = "Top-Fermented";

    @Column
    String yeast = "Saccharomyces cerevisiae";

}
