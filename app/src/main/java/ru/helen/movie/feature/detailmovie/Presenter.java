package ru.helen.movie.feature.detailmovie;

import android.util.Log;

import javax.inject.Inject;

import ru.helen.movie.model.DetailMovie;

/**
 * Presenter for detailmovie
 */

public class Presenter implements Contractor.OnLoadDetail{
    private Contractor.ViewDetail view;
    private Contractor.Interactor  interactor;

    @Inject
    public Presenter(Contractor.ViewDetail view,
                     Contractor.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void getMovieDetail(int id, String token, String language){
        interactor.getDetailMovie(id, token, language, this);
    }

    @Override
    public void onSuccessLoadDetail(DetailMovie detailMovie) {
        view.onShowDetailMovie(detailMovie);
    }

    @Override
    public void onErrorLoadDetail(String error) {
        Log.e("MOVIE", "ERRORDETAIL");
    }
}
