package com.example.tmbdmovies.RetrofitSingleton;

import com.example.tmbdmovies.Interface.MovieApiInterface;
import com.example.tmbdmovies.Utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sservice {

    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    public static MovieApiInterface movieApi =  retrofit.create(MovieApiInterface.class);

    public static MovieApiInterface getMovieApi() {
        return movieApi;
    }
}

