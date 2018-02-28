package ru.helen.movie.di;

import dagger.Module;
import dagger.Provides;
import ru.helen.movie.feature.detailmovie.Contractor;
import ru.helen.movie.feature.detailmovie.InteractorImpl;
import ru.helen.movie.feature.detailmovie.Presenter;
import ru.helen.movie.repository.DatabaseRepository;
import ru.helen.movie.repository.NetworkRepository;

/**
 * Module for feature detail.
 */
@Module
public class DetailModule {
    private Contractor.ViewDetail view;

    public DetailModule(Contractor.ViewDetail view) {
        this.view = view;
    }

    @DetailScope
    @Provides
    Contractor.ViewDetail provideView(){return view;}

    @DetailScope
    @Provides
    Contractor.Interactor provideInteractor(NetworkRepository networkRepository, DatabaseRepository databaseRepository){
        return new InteractorImpl(networkRepository, databaseRepository);
    }

    @DetailScope
    @Provides
    Presenter providePresenter(Contractor.ViewDetail view, Contractor.Interactor interactor){
        return new Presenter(view,interactor);
    }
}
