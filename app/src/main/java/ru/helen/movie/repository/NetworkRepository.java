package ru.helen.movie.repository;

import io.reactivex.Observable;
import ru.helen.movie.model.DetailMovie;
import ru.helen.movie.model.PageMovie;


/**
 * Interface for Network Repository
 */

public interface NetworkRepository {
    Observable<PageMovie> getMovieTopRated(String apiKey, String language, int page);
    Observable<PageMovie> getMoviePopular(String apiKey, String language, int page);
    Observable<DetailMovie> getDetailMovie(int id, String apiKey, String language);
}
