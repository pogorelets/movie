package ru.helen.movie.feature;

import java.util.List;

import ru.helen.movie.model.Movie;

/**
 * For ViewState
 */

public class MovieState {
    private String flag;
    private int pageall;
    private int pagetop;
    private List<Movie> movies;

    public MovieState(String flag, int pageall, int pagetop, List<Movie> movies) {
        this.flag = flag;
        this.pageall = pageall;
        this.pagetop = pagetop;
        this.movies = movies;
    }

    public String getFlag() {
        return flag;
    }

    public int getPageall() {
        return pageall;
    }

    public int getPagetop() {
        return pagetop;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
