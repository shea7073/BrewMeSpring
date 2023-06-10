package com.CS622.BrewMe.entity;

import jakarta.persistence.*;

@Entity(name = "rate_beer_review")
public class RateBeerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer beerId;

    @Column
    private Integer brewerId;

    @Column
    private Double abv;

    @Column
    private String style;

    @Column
    private Double appearance;

    @Column
    private Double aroma;

    @Column
    private Double palate;

    @Column
    private Double taste;

    @Column
    private Double overall;

    @Column
    private Integer time;

    @Column
    private String profileName;

    @Lob
    @Column
    private String text;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBeerId() {
        return beerId;
    }

    public void setBeerId(Integer beerId) {
        this.beerId = beerId;
    }

    public Integer getBrewerId() {
        return brewerId;
    }

    public void setBrewerId(Integer brewerId) {
        this.brewerId = brewerId;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getAppearance() {
        return appearance;
    }

    public void setAppearance(Double appearance) {
        this.appearance = appearance;
    }

    public Double getAroma() {
        return aroma;
    }

    public void setAroma(Double aroma) {
        this.aroma = aroma;
    }

    public Double getPalate() {
        return palate;
    }

    public void setPalate(Double palate) {
        this.palate = palate;
    }

    public Double getTaste() {
        return taste;
    }

    public void setTaste(Double taste) {
        this.taste = taste;
    }

    public Double getOverall() {
        return overall;
    }

    public void setOverall(Double overall) {
        this.overall = overall;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
