package ru.helen.movie.feature.movie;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ru.helen.movie.model.Movie;

/**
 * Presenter for feature topmovie
 */

public class Presenter implements Contractor.OnMoviesLoadedListener{
    private Contractor.ViewTopMovie view;
    private Contractor.Interactor  interactor;

    @Inject
    public Presenter(Contractor.ViewTopMovie view,
                     Contractor.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void getAllMovies(String token, String language, int page){
        interactor.getMoviePopular(token, language, page,  this);
    }

    public void getTopMovie(String token, String language, int page){
        interactor.getMovieTopRated(token,language, page, this);
    }

    @Override
    public void onLoadMoviesError(String error) {
        Log.e("MOVIE",error);
    }

    @Override
    public void onLoadMoviesSuccess(List<Movie> movie) {
          view.updateAdapter(movie);Log.e("MOVIE", movie.toString());
    }
}
