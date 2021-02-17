package com.example.tmbdmovies.Interface;

import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.example.tmbdmovies.Responses.SearchMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {

    //Search for movies
    //Query is used to sort and search items.
   // https://api.themoviedb.org/3/search/movie?api_key=a777a4fb5a673b4bc508079a69c7cf83&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<SearchMovieResponse> searchMovie(

            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page);

    //Search by IDs
    @GET("/3/movie/{movie_id}?")
    Call<PopularMovieModal> getMovies(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key);

    //Get PopularMovies
    //https://api.themoviedb.org/3/movie/?api_key=a777a4fb5a673b4bc508079a69c7cf83
    @GET("/3/movie/popular?")
    Call<SearchMovieResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page);



}
