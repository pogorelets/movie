package ru.helen.movie.di;

import dagger.Module;
import dagger.Provides;
import ru.helen.movie.feature.movie.Contractor;
import ru.helen.movie.feature.movie.InteractorImpl;
import ru.helen.movie.feature.movie.Presenter;
import ru.helen.movie.repository.NetworkRepository;

/**
 * Module for TopMovie.
 */
@Module
public class MovieModule {
    private Contractor.ViewTopMovie view;

    public MovieModule(Contractor.ViewTopMovie view) {
        this.view = view;
    }

    @MovieScope
    @Provides
    Contractor.ViewTopMovie provideView(){
        return view;
    }

    @MovieScope
    @Provides
    Contractor.Interactor provideIneractor(NetworkRepository networkRepository){
        return new InteractorImpl(networkRepository);
    }

    @MovieScope
    @Provides
    Presenter providePresenter(Contractor.ViewTopMovie view, Contractor.Interactor interactor){
        return new Presenter(view,interactor);
    }
}
