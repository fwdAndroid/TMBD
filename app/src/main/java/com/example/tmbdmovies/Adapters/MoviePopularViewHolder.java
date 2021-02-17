package com.example.tmbdmovies.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmbdmovies.R;

public class MoviePopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView1;
    RatingBar ratingBar1;

    OnMovieListner onMovieListner;

    public MoviePopularViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public MoviePopularViewHolder(@NonNull View itemView, OnMovieListner onMovieListner) {
        super(itemView);


        this.onMovieListner = onMovieListner;
;

        imageView1 = itemView.findViewById(R.id.movie_img1);
        ratingBar1 = itemView.findViewById(R.id.rating_bar1);

        itemView.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {

        onMovieListner.onMovieClick(getAdapterPosition());
    }
}