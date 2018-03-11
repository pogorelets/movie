package ru.helen.movie.feature.movie;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.helen.movie.feature.MovieState;
import ru.helen.movie.model.Movie;
import ru.helen.movie.model.PageMovie;

import static ru.helen.movie.feature.main.MainActivity.POPULAR;

/**
 * Presenter for feature topmovie
 */

public class Presenter implements Contractor.OnMoviesLoadedListener{
    private Contractor.ViewTopMovie view;
    private Contractor.Interactor  interactor;
    private String flag = POPULAR;
    private int pageall = 1;
    private int pagetop = 1;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getPageall() {
        return pageall;
    }

    public void setPageall(int pageall) {
        this.pageall = pageall;
    }

    public int getPagetop() {
        return pagetop;
    }

    public void setPagetop(int pagetop) {
        this.pagetop = pagetop;
    }

    public void loadnext(String token, String language){
        if (flag.equals(POPULAR)) {
            pageall = pageall + 1;
            getAllMovies(token, language);
        } else{
            pagetop = pagetop + 1;
            getTopMovie(token,language);
        }
    }

    @Inject
    public Presenter(Contractor.ViewTopMovie view,
                     Contractor.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void getAllMovies(String token, String language){
        interactor.getMoviePopular(token, language, pageall,  this);
    }

    public void getTopMovie(String token, String language){
        interactor.getMovieTopRated(token,language, pagetop, this);
    }

    @Override
    public void onLoadMoviesError(String error) {
        Log.e("MOVIE",error);
    }

    @Override
    public void onLoadMoviesSuccess(PageMovie page) {
          //TODO
           view.updateAdapter(page.getResults());
    }

    public MovieState getMovieState(){
        List<Movie> movies = new ArrayList<>();
        MovieState state = new MovieState(flag,pageall,pagetop, movies);
        Log.e("PAGEALL", String.valueOf(state.getPageall()));
        return state;
    }
}
