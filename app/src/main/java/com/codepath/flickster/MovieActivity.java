package com.codepath.flickster;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.flickster.adapters.MoviesAdapter;
import com.codepath.flickster.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.codepath.flickster.R.id.rvMovies;

public class MovieActivity extends AppCompatActivity {

    List<Movie> movies;
    RecyclerView listMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        MovieData movieData = new MovieData();
        movieData.execute();
    }

    private class MovieData extends AsyncTask<Void,Void,Void>{
        String data = "";
        String strUrl = getString(R.string.TMDB_MOVIE_API);
        String imageUrl = getString(R.string.TMDB_MOVIE_IMAGE_API);
        String tmdbApiKey = getString(R.string.TMDB_MOVIE_API_KEY);

        @Override
        protected Void doInBackground(Void... voids){
            try{
                URL url = new URL(strUrl+ "/movie/now_playing?api_key=" +tmdbApiKey);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line!=null){
                    line = br.readLine();
                    data = data + line;
                }

                movies = new ArrayList<Movie>();
                try {
                    Log.d("Received data from TMDB: ", data);
                    JSONObject json = new JSONObject(data);
                    JSONArray moviesR = (JSONArray) json.get("results");
                    for (int i=0; i<moviesR.length(); i++) {
                        JSONObject o = (JSONObject) moviesR.get(i);
                        movies.add(new Movie(
                                imageUrl + "w500" + o.getString("poster_path") + "?api_key=" +tmdbApiKey,
                                o.getString("title"), o.getString("overview"),
                                imageUrl + "w1280" + o.getString("backdrop_path") + "?api_key=" +tmdbApiKey,
                                o.getDouble("vote_average")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            listMovies = (RecyclerView) findViewById(rvMovies);
            MoviesAdapter adapter = new MoviesAdapter(MovieActivity.this, movies);
            // Attach the adapter to the recyclerview to populate items
            listMovies.setAdapter(adapter);
            // Set layout manager to position the items
            listMovies.setLayoutManager(new LinearLayoutManager(MovieActivity.this));

        }
    }
}
