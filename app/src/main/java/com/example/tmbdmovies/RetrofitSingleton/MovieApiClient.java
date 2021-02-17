package com.example.tmbdmovies.RetrofitSingleton;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tmbdmovies.Executors.AppExecutors;
import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.example.tmbdmovies.Responses.SearchMovieResponse;
import com.example.tmbdmovies.Respositary.MovieRespositary;
import com.example.tmbdmovies.Utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    static MovieApiClient instance;


    //Live Data for Search
    MutableLiveData<List<PopularMovieModal>> mMovies;
    //Making Global Runnable Request Search
    ReteriveMovieRunnable reteriveMovieRunnable;


    //Live Data for Popular Movies
    MutableLiveData<List<PopularMovieModal>> mMoviesPopular;
    //Making Global Runnable Request Popular Search
    ReteriveMovieRunnablePop reteriveMovieRunnablePopular;






    public static MovieApiClient getInstance(){
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    public MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mMoviesPopular = new MutableLiveData<>();
    }


    public LiveData<List<PopularMovieModal>> getMovies() {
        return mMovies;
    }

    public LiveData<List<PopularMovieModal>> getPopular(){
        return mMoviesPopular;
    }

    //-1 Movie Search Api we are used for going to call through the classes
    public void searchMovieApi(String query,int pageNumber){
        if (reteriveMovieRunnable != null){
            reteriveMovieRunnable = null;
        }
        reteriveMovieRunnable = new ReteriveMovieRunnable(query,pageNumber);


        final Future myHandler = AppExecutors.getInstance().networkIO().submit(reteriveMovieRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                //Cancel Retrofit Request
                myHandler.cancel(true);
            }
        },4000, TimeUnit.MILLISECONDS);

    }

    //-2.1  Popular Movie Search Api we are used for going to call through the classes
    public void searchMovieApiPop(int pageNumber){
        if (reteriveMovieRunnablePopular != null){
            reteriveMovieRunnablePopular = null;
        }
        reteriveMovieRunnablePopular = new ReteriveMovieRunnablePop(pageNumber);


        final Future myHandler2 = AppExecutors.getInstance().networkIO().submit(reteriveMovieRunnablePopular);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                //Cancel Retrofit Request
                myHandler2.cancel(true);
            }
        },1000, TimeUnit.MILLISECONDS);

    }









    //Reterive Data from RestApi by RUNNABLE
    class ReteriveMovieRunnable implements Runnable{

        String query;
        int pageNumber;
        boolean cancelRequest;

        public ReteriveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

            //Getting THe Response objects
            try {

                Response response = getMovies(query,pageNumber).execute();

                if (cancelRequest){
                    return;
                }

                if (response.code() == 200){
                    List<PopularMovieModal> list = new ArrayList<>(((SearchMovieResponse)response.body()).getMovies());
                    if (pageNumber == 1){
                        //Sending Data to Live Data
                        //PostVAlue:  used of background thread
                        //setValue
                        mMovies.postValue(list);
                    }else {
                        List<PopularMovieModal> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }else {
                    String error = response.errorBody().string();
                    Log.v("Tag","Error"+error);
                    mMovies.postValue(null);
                }

            }catch (IOException e){
                e.printStackTrace();
                mMovies.postValue(null);
            }



        }

        private Call<SearchMovieResponse> getMovies(String query, int pageNumber){
            return sservice.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );
        }

        private void cancelRequest(){
            Log.v("Tag","Request is Cancelled");
            cancelRequest = true;
        }

    }



    //Reterive Data from RestApi by RUNNABLE Popular
    class ReteriveMovieRunnablePop implements Runnable{

        int pageNumber;
        boolean cancelRequest;

        public ReteriveMovieRunnablePop(int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

            //Getting THe Response objects
            try {

                Response response1 = getPop(pageNumber).execute();

                if (cancelRequest){
                    return;
                }

                if (response1.code() == 200){
                    List<PopularMovieModal> list = new ArrayList<>(((SearchMovieResponse)response1.body()).getMovies());
                    if (pageNumber == 1){
                        //Sending Data to Live Data
                        //PostVAlue:  used of background thread
                        //setValue
                        mMoviesPopular.postValue(list);
                    }else {
                        List<PopularMovieModal> currentMovies = mMoviesPopular.getValue();
                        currentMovies.addAll(list);
                        mMoviesPopular.postValue(currentMovies);
                    }
                }else {
                    String error = response1.errorBody().string();
                    Log.v("Tag","Error"+error);
                    mMoviesPopular.postValue(null);
                }

            }catch (IOException e){
                e.printStackTrace();
                mMoviesPopular.postValue(null);
            }



        }

        private Call<SearchMovieResponse> getPop(int pageNumber){
           return sservice.getMovieApi().getPopular(
                   Credentials.API_KEY,
                   pageNumber
           );
        }

        private void cancelRequest(){
            Log.v("Tag","Request is Cancelled");
            cancelRequest = true;
        }

    }

}
