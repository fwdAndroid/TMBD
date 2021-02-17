package com.example.tmbdmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tmbdmovies.Adapters.MovieViewHolder;
import com.example.tmbdmovies.Modals.PopularMovieModal;

public class Movie_Details extends AppCompatActivity {

    TextView txt_movie_desc,txt_movie_title;
    RatingBar rating_bar_details;
    ImageView imageViewPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__details);

        imageViewPoster = findViewById(R.id.imageViewPoster);
        txt_movie_title = findViewById(R.id.txt_movie_title);
        txt_movie_desc  = findViewById(R.id.txt_movie_desc);
        rating_bar_details = findViewById(R.id.rating_bar_details);

        //GetDataFromMainActivity
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")){
            PopularMovieModal pop = getIntent().getParcelableExtra("movie");
            Log.v("TagID","Incoming Data"+pop.getTitle());

            txt_movie_title.setText(pop.getTitle());
            txt_movie_desc.setText(pop.getOverview());
            rating_bar_details.setRating((pop.getVote_average())/2);
          rating_bar_details.setRating((pop.getVote_average())/2);


            Glide.with(this).load("https://image.tmdb.org/t/p/w500"+pop.getPoster_path()).into(imageViewPoster);

        }
    }
}