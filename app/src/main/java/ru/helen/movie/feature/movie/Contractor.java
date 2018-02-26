package ru.helen.movie.feature.movie;

import java.util.List;

import ru.helen.movie.model.Movie;

/**
 * Contractor for feature MovieFragment
 */

public interface Contractor {
    interface OnMovieClick {
        void onShowMovieDetail(Movie movie);
    }
    interface ViewTopMovie{
        void updateAdapter(List<Movie> movies);

    }

    interface OnMoviesLoadedListener {
        void onLoadMoviesError(String error);
        void onLoadMoviesSuccess(List<Movie> movie);
    }

    interface Interactor{
        void getMovieTopRated(String token, String language, int page, OnMoviesLoadedListener listener);
        void getMoviePopular(String token, String language, int page, OnMoviesLoadedListener listener);
    }
}
