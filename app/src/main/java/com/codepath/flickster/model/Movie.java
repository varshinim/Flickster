package com.codepath.flickster.model;

import java.util.ArrayList;


public class Movie {
    private String movieImage;
    private String movieTitle;
    private String movieOverview;

    public Movie(String image, String title, String overview){
        movieImage = image;
        movieTitle = title;
        movieOverview = overview;
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

}
