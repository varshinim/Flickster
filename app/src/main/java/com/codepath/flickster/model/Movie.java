package com.codepath.flickster.model;

import java.util.ArrayList;


public class Movie {
    private String movieImage;
    private String movieTitle;
    private String movieOverview;
    private String backdropImage;
    private Double popularity;

    public Movie(String image, String title, String overview, String backdropImage, Double popularity){
        movieImage = image;
        movieTitle = title;
        movieOverview = overview;
        this.backdropImage = backdropImage;
        this.popularity = popularity;
    }

    public String getImage(){
        return movieImage;
    }

    public String getTitle(){
        return movieTitle;
    }

    public String getOverview(){
        return movieOverview;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public Double getPopularity() { return popularity; };

}
