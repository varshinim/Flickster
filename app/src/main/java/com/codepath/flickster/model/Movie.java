package com.codepath.flickster.model;

import java.util.ArrayList;


public class Movie {
    private String movieImage;
    private String movieTitle;
    private String movieOverview;
    private String backdropImage;

    public Movie(String image, String title, String overview, String backdropImage){
        movieImage = image;
        movieTitle = title;
        movieOverview = overview;
        this.backdropImage = backdropImage;
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

}
