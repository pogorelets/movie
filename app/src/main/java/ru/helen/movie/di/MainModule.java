package ru.helen.movie.di;

import dagger.Module;
import dagger.Provides;
import ru.helen.movie.feature.main.Contractor;
import ru.helen.movie.feature.main.InteractorImpl;
import ru.helen.movie.feature.main.Presenter;
import ru.helen.movie.repository.NetworkRepository;

/**
 * Module for main feature.
 */
@Module
public class MainModule {
    private final Contractor.ViewMain view;

    public MainModule(Contractor.ViewMain view) {
        this.view = view;
    }

    @MainScope
    @Provides
    Contractor.ViewMain provideView(){return view;}

    @MainScope
    @Provides
    Contractor.Interactor provideInteractor(NetworkRepository networkRepository){
        return new InteractorImpl(networkRepository);
    }

    @MainScope
    @Provides
    Presenter providePresenter(Contractor.ViewMain view, Contractor.Interactor interactor){
        return new Presenter(view, interactor);
    }

}
