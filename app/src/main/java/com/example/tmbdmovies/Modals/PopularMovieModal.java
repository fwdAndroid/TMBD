package com.example.tmbdmovies.Modals;

import android.os.Parcel;
import android.os.Parcelable;

public class PopularMovieModal implements Parcelable {

    String title;
    String poster_path;
    String release_date;
    int movie_id;
    float vote_average;
    String overview;
    String original_language;

    public PopularMovieModal(String title, String poster_path, String release_date, int movie_id, float vote_average, String overview,String original_language) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.original_language = original_language;
    }

    protected PopularMovieModal(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
        original_language = in.readString();
    }

    public static final Creator<PopularMovieModal> CREATOR = new Creator<PopularMovieModal>() {
        @Override
        public PopularMovieModal createFromParcel(Parcel in) {
            return new PopularMovieModal(in);
        }

        @Override
        public PopularMovieModal[] newArray(int size) {
            return new PopularMovieModal[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeFloat(vote_average);
        dest.writeString(overview);
        dest.writeString(original_language);
    }

    @Override
    public String toString() {
        return "PopularMovieModal{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_id=" + movie_id +
                ", vote_average=" + vote_average +
                ", overview='" + overview + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
