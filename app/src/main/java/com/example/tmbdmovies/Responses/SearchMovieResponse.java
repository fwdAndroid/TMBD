package com.example.tmbdmovies.Responses;

import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//This class is used to getting multiple movies (movies list) -> Popular Movies list
public class SearchMovieResponse {

    @SerializedName("total_results")
    @Expose()
    private int total_count;



    @SerializedName("results")
    @Expose()
    private List<PopularMovieModal> movies;


    public int getTotal_count() {
        return total_count;
    }



    public List<PopularMovieModal> getMovies() {
        return movies;
    }
    @Override
    public String toString() {
        return "SearchMovieResponse{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}
