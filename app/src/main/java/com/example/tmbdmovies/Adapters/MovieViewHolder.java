package com.example.tmbdmovies.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmbdmovies.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title,duration,release_date;
    ImageView imageView;
    RatingBar ratingBar;

    OnMovieListner onMovieListner;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public MovieViewHolder(@NonNull View itemView, OnMovieListner onMovieListner) {
        super(itemView);


        this.onMovieListner = onMovieListner;

        title = itemView.findViewById(R.id.txtMovieTitle);
        duration = itemView.findViewById(R.id.txtMovieDuration);
        release_date = itemView.findViewById(R.id.txtMovieRealse);

        imageView = itemView.findViewById(R.id.movie_img);
        ratingBar = itemView.findViewById(R.id.rating_bar);

        itemView.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {

        onMovieListner.onMovieClick(getAdapterPosition());
    }
}