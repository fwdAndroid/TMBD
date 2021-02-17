package com.example.tmbdmovies.ViewModal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.example.tmbdmovies.Respositary.MovieRespositary;

import java.util.List;

public class ViewModaMovieLiveData extends ViewModel {

  //Instance of Respositary
    MovieRespositary movieRespositary;
    //View Modal Class

    public ViewModaMovieLiveData() {

        movieRespositary = new MovieRespositary().getMovieResposiaryInstance();
    }

    public LiveData<List<PopularMovieModal>> getmMovies() {
        return movieRespositary.getMovies();
    }

    public LiveData<List<PopularMovieModal>> getPop() {
        return movieRespositary.getPopular();
    }


    //-3 Calling method in view-modals
    public void searchMovieApi(String query, int pageNumber){
        movieRespositary.searchMovieApi(query,pageNumber);
    }

    //2-2 Calling method in view-modals
    public void searchMovieApiPop(int pageNumber){
        movieRespositary.searchMovieApiPop(pageNumber);
    }

    public void searchNextPage(){

        movieRespositary.searchNextPage();
    }
}

