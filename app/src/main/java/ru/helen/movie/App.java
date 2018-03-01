package ru.helen.movie;

import android.app.Application;
import android.content.Context;

import ru.helen.movie.di.AppComponent;
import ru.helen.movie.di.AppModule;
import ru.helen.movie.di.DaggerAppComponent;
import ru.helen.movie.di.DetailComponent;
import ru.helen.movie.di.DetailModule;
import ru.helen.movie.di.MainComponent;
import ru.helen.movie.di.MainModule;
import ru.helen.movie.di.MovieModule;
import ru.helen.movie.di.MovieComponent;
import ru.helen.movie.feature.detailmovie.DetailFragment;
import ru.helen.movie.feature.main.MainActivity;
import ru.helen.movie.feature.movie.MovieFragment;


public class App extends Application {
    private AppComponent appComponent;
    private DetailComponent detailComponent;
    private MainComponent mainComponent;
    private MovieComponent topMovieComponent;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext())).build();
    }

    public MainComponent initMainComponent(MainActivity activity) {
        mainComponent = appComponent.getMainComponent(new MainModule(activity));
        return mainComponent;
    }

    public DetailComponent initDetailComponent(DetailFragment fragment){
        detailComponent = appComponent.getDetailComponent(new DetailModule(fragment));
        return detailComponent;
    }

    public MovieComponent initTopMovieComponent(MovieFragment fragment){
        topMovieComponent = appComponent.getTopMovieComponent(new MovieModule(fragment));
        return topMovieComponent;
    }


    public void destroyMainComponent(){
        mainComponent = null;
    }

    public void destroyDetailComponent(){
        detailComponent = null;
    }

    public void destroyTopMovieComponent(){
        topMovieComponent = null;
    }

}
