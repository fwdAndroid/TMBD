package com.example.tmbdmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.tmbdmovies.Adapters.MovieRecyclerView;
import com.example.tmbdmovies.Adapters.OnMovieListner;
import com.example.tmbdmovies.Modals.PopularMovieModal;

import com.example.tmbdmovies.ViewModal.ViewModaMovieLiveData;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMovieListner {
RecyclerView recyclerView;
Toolbar toolbar;

    //View Modal Initialization
    ViewModaMovieLiveData viewModaMovieLiveData;
    MovieRecyclerView movieAdapter;

    boolean isPopular = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        viewModaMovieLiveData = new ViewModelProvider(this).get(ViewModaMovieLiveData.class);


        //SearchView
        setupSearchView();
        
        //Calling Recycler View
        configingRecyclerView();
        

        // Calling Observer
        observeAnyChange();
        observeAnyChangePopular();


        viewModaMovieLiveData.searchMovieApiPop(1);




}

//-4  Get Data from searchview & query the api to get the results.
// Calling MovieSearchApi Here
    private void setupSearchView() {
        final SearchView searchView  = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModaMovieLiveData.searchMovieApi(
                        //Search String getting from search view
                        query,
                        1
                );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //Popular Movies
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular = false;
            }
        });
    }


// -5 Configuring RecyclerView And Put API Data Inside It
    public void configingRecyclerView(){

        movieAdapter = new MovieRecyclerView(this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        //Recycler Pagination
        //Display all pages result using api response
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)){
                    //Here we need to display the result of next pages using api responses
                    viewModaMovieLiveData.searchNextPage();
                }
            }
        });

    }

    //Obeserver are used to notify the changes in live data
    public void observeAnyChange(){
        viewModaMovieLiveData.getmMovies().observe(this, new Observer<List<PopularMovieModal>>() {
            @Override
            public void onChanged(List<PopularMovieModal> popularMovieModals) {
                //Observing Changing
                if (popularMovieModals != null){
                    for (PopularMovieModal popmoviesModals : popularMovieModals){
                        //Get DAta in Log
                        Log.v("Tag","onChanged:"+popmoviesModals.getTitle());
                        movieAdapter.setmMovies(popularMovieModals);
                    }
                }
            }
        });



    }

    public void observeAnyChangePopular(){
        //Popular
        viewModaMovieLiveData.getPop().observe(this, new Observer<List<PopularMovieModal>>() {
            @Override
            public void onChanged(List<PopularMovieModal> popularMovieModals) {
                //Observing Changing
                if (popularMovieModals != null){
                    for (PopularMovieModal popmoviesModals : popularMovieModals){
                        //Get DAta in Log
                        Log.v("Tag","onChanged:"+popmoviesModals.getTitle());
                        movieAdapter.setmMovies(popularMovieModals);
                    }
                }
            }
        });
    }



    @Override
    public void onMovieClick(int position) {

        //We don't need position of the movie in recycler view
        //We need the ID of the movie in order to get all it's details.
        // We get This id from recycler view adapter

        Intent intent = new Intent(this,Movie_Details.class);
        intent.putExtra("movie",movieAdapter.getSelectedMovieID(position));
        startActivity(intent);


    }

    @Override
    public void onCategoryClick(String category) {

    }
}