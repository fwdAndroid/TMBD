package com.example.tmbdmovies.Executors;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.example.tmbdmovies.Responses.SearchMovieResponse;
import com.example.tmbdmovies.RetrofitSingleton.sservice;
import com.example.tmbdmovies.Utils.Credentials;

import java.io.IOException;
import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class AppExecutors {

    static AppExecutors instance;



    public static AppExecutors getInstance(){
        if (instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }



    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }




}
