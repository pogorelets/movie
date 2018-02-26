package ru.helen.movie.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * DI Parent component
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    MainComponent getMainComponent(MainModule mainModule);
    DetailComponent getDetailComponent(DetailModule detailModule);
    MovieComponent getTopMovieComponent(MovieModule topMovieModule);
}
