package com.example.tmbdmovies.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmbdmovies.Modals.PopularMovieModal;
import com.example.tmbdmovies.R;
import com.example.tmbdmovies.Utils.Credentials;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<PopularMovieModal> mMovies = new ArrayList<>();
    OnMovieListner movieListener;

    static final int Display_Pop = 1;
    static final int Display_Search = 2;

    public MovieRecyclerView(OnMovieListner movieListener) {
        this.movieListener = movieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);

      View v = null;
      if (viewType == Display_Search){
           v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
          return new MovieViewHolder(v, movieListener);
      }else {
          v = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_layout,parent,false);
          return new MoviePopularViewHolder(v, movieListener);
      }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        if (itemViewType == Display_Search){

            ((MovieViewHolder)holder).title.setText(mMovies.get(position).getTitle());
            ((MovieViewHolder)holder).release_date.setText(mMovies.get(position).getRelease_date());

            ((MovieViewHolder)holder).duration.setText(mMovies.get(position).getOriginal_language());
            ((MovieViewHolder)holder).ratingBar.setRating((mMovies.get(position).getVote_average())/2);
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+mMovies.get(position).getPoster_path()).into(((MovieViewHolder)holder).imageView);

        }else {
            ((MoviePopularViewHolder)holder).ratingBar1.setRating((mMovies.get(position).getVote_average())/2);
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+mMovies.get(position).getPoster_path()).into(((MoviePopularViewHolder)holder).imageView1);

        }

    }


    @Override
    public int getItemCount() {

            return mMovies.size();

    }

    public void setmMovies(List<PopularMovieModal> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    // Getiing the id of movie
    public PopularMovieModal getSelectedMovieID(int position){
        if (mMovies != null){
            if (mMovies.size() > 0){
                return mMovies.get(position);
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (Credentials.Popular){
            return Display_Pop;
        }
        else {
            return Display_Search;
        }
    }
}
