package com.example.tmbdmovies.Respositary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tmbdmovies.Executors.AppExecutors;
import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.example.tmbdmovies.RetrofitSingleton.MovieApiClient;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MovieRespositary {
    //This class is working as respositary
    MovieRespositary movieRespositaryInstance;
    MovieApiClient movieApiClient;

    String mQuery;
    int mPageNumber;

    public MovieRespositary getMovieResposiaryInstance() {
        if (movieRespositaryInstance == null) {
            movieRespositaryInstance = new MovieRespositary();
        }
        return movieRespositaryInstance;
    }

    public MovieRespositary() {
        movieApiClient   = MovieApiClient.getInstance();
    }



    public LiveData<List<PopularMovieModal>> getMovies(){
        return movieApiClient.getMovies();
    }


    public LiveData<List<PopularMovieModal>> getPopular(){
        return movieApiClient.getPopular();
    }



    //-2 Calling searchmovieapi method im movie respositary
    public void searchMovieApi(String query, int pageNumber){
         mQuery = query;
         mPageNumber = pageNumber;

        movieApiClient.searchMovieApi(query,pageNumber);
    }


    //-2.1 Calling searchmovieapiPpular method im movie respositary
    public void searchMovieApiPop(int pageNumber){
        mPageNumber = pageNumber;
        movieApiClient.searchMovieApiPop(pageNumber);
    }



    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }
}
