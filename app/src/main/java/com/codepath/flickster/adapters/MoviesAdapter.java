package com.codepath.flickster.adapters;


import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;
        public TextView movieTitle;
        public TextView movieOverview;

        public ViewHolder(View movie) {
            super(movie);
            movieImage = (ImageView) movie.findViewById(R.id.ivMovieImage);
            movieTitle = (TextView) movie.findViewById(R.id.tvMovieTitle);
            movieOverview = (TextView) movie.findViewById(R.id.tvMovieOverview);
        }
    }

    private List<Movie> movieList;
    private Context context;
    public MoviesAdapter(Context context, List<Movie> movieList){
        this.movieList = movieList;
        this.context = context;
    }

    /*private Context getContext(){
        return context;
    }*/

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.lv_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder viewHolder, int position){
        Movie movie = movieList.get(position);

        String image = null;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            image = movie.getImage();
        } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            image = movie.getBackdropImage();
        }

        ImageView imageView = viewHolder.movieImage;
        Picasso.with(context).load(image).into(imageView);
        TextView textViewt = viewHolder.movieTitle;
        textViewt.setText(movie.getTitle());
        TextView textViewo = viewHolder.movieOverview;
        textViewo.setText(movie.getOverview());
    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }
}
