package ru.helen.movie.feature.detailmovie;

import ru.helen.movie.model.DetailMovie;


/**
 * Contractor for feature DetailFragment
 */

public interface Contractor {
    interface ViewDetail{
        void onShowDetailMovie(DetailMovie detail);

    }

    interface OnLoadDetail{
        void onSuccessLoadDetail(DetailMovie detailMovie);
        void onErrorLoadDetail(String error);
    }
    interface Interactor{
        void getDetailMovie(int id, String token, String language, OnLoadDetail listener);
    }
}
