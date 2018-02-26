package ru.helen.movie.repository;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.helen.movie.model.DetailMovie;
import ru.helen.movie.model.PageMovie;


public interface ThemoviedbAPI {

    @GET("3/movie/top_rated")
    Observable<PageMovie> getMovieTopRated(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("3/movie/popular")
    Observable<PageMovie> getMoviePopular(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Observable<DetailMovie> getDetailMovie(@Path("movie_id") int id, @Query("api_key") String apiKey, @Query("language") String language);
}
