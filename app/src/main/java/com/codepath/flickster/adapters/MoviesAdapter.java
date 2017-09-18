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

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

        public ImageView getMovieImage() { return this.movieImage; }
        public void setMovieImage(ImageView movieImage) { this.movieImage = movieImage; }
        public TextView getMovieTitle() { return this.movieTitle; }
        public void setMovieTitle(TextView movieTitle) { this.movieTitle = movieTitle; }
        public TextView getMovieOverview() { return this.movieOverview; }
        public void setMovieOverview(TextView movieOverview) { this.movieOverview = movieOverview; }
    }

    public class PopularMovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;

        public PopularMovieViewHolder(View movie) {
            super(movie);
            movieImage = (ImageView) movie.findViewById(R.id.ivMovieImage);
        }

        public ImageView getMovieImage() { return this.movieImage; }
        public void setMovieImage(ImageView movieImage) { this.movieImage = movieImage; }
    }

    private List<Movie> movieList;
    private Context context;
    public MoviesAdapter(Context context, List<Movie> movieList){
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == 0 &&
                context.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            movieView = inflater.inflate(R.layout.popular_movie_layout, parent, false);
            viewHolder = new PopularMovieViewHolder(movieView);
        } else {
            movieView = inflater.inflate(R.layout.lv_layout, parent, false);
            viewHolder = new ViewHolder(movieView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position){
        Movie movie = movieList.get(position);

        String image = null;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            image = movie.getImage();
        } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            image = movie.getBackdropImage();
        }

        if (viewHolder.getItemViewType() == 0 &&
                context.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            PopularMovieViewHolder vh = (PopularMovieViewHolder) viewHolder;
            image = movie.getBackdropImage();
            ImageView imageView = vh.getMovieImage();
            Picasso.with(context).load(image).placeholder(R.drawable.movie_placeholder).into(imageView);
        } else {
            ViewHolder vh = (ViewHolder) viewHolder;
            ImageView imageView = vh.getMovieImage();
            Picasso.with(context).load(image).placeholder(R.drawable.movie_placeholder).into(imageView);
            TextView textViewt = vh.getMovieTitle();
            textViewt.setText(movie.getTitle());
            TextView textViewo = vh.getMovieOverview();
            textViewo.setText(movie.getOverview());
        }
    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = movieList.get(position);
        return movie.getPopularity() > 5.0 ? 0:1;
    }
}
