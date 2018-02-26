package ru.helen.movie.feature.main;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ru.helen.movie.model.Movie;

/**
 * Presenter for feature Main
 */

public class Presenter  {
    private Contractor.ViewMain view;
    private Contractor.Interactor  interactor;

    @Inject
    public Presenter(Contractor.ViewMain view,
                     Contractor.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


}
