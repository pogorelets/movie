package ru.helen.movie.repository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.helen.movie.model.DetailMovie;
import ru.helen.movie.model.PageMovie;


/**
 * Network Repository
 */

public class NetworkRepositoryImpl implements NetworkRepository {
    private final ThemoviedbAPI themoviedbAPI;

    public NetworkRepositoryImpl(ThemoviedbAPI themoviedbAPI){
        this.themoviedbAPI = themoviedbAPI;
    }

    @Override
    public Observable<PageMovie> getMovieTopRated(String apiKey, String language, int page) {
        return themoviedbAPI.getMovieTopRated(apiKey,language,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PageMovie> getMoviePopular(String apiKey, String language, int page) {
        return themoviedbAPI.getMoviePopular(apiKey,language,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<DetailMovie> getDetailMovie(int id, String apiKey, String language) {
        return themoviedbAPI.getDetailMovie(id,apiKey,language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
