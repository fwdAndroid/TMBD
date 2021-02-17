package com.example.tmbdmovies.Responses;

import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Searh single movie result
public class MovieResponse {

    @SerializedName("results")
    @Expose

    private PopularMovieModal movie;

    public PopularMovieModal getMovieModal() {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
