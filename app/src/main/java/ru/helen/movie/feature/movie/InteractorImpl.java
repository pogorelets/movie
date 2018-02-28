package ru.helen.movie.feature.movie;

import ru.helen.movie.model.PageMovie;
import ru.helen.movie.repository.DatabaseRepository;
import ru.helen.movie.repository.NetworkRepository;

/**
 * Interactor for feature MovieFragment
 */

public class InteractorImpl implements Contractor.Interactor {
    private NetworkRepository networkRepository;
    private DatabaseRepository databaseRepository;

    public InteractorImpl(NetworkRepository networkRepository, DatabaseRepository databaseRepository) {
        this.networkRepository = networkRepository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public void getMovieTopRated(String token, String language, int pager, Contractor.OnMoviesLoadedListener listener) {
        PageMovie pageMovie = databaseRepository.getTopRatedPage(pager);
        if (pageMovie != null){
            listener.onLoadMoviesSuccess(pageMovie);
        }else{
            networkRepository.getMovieTopRated(token, language, pager).subscribe(page -> {
                        listener.onLoadMoviesSuccess(page);
                        databaseRepository.insertPageTopRated(page);
                    },
                    throwable -> listener.onLoadMoviesError(throwable.toString()));
        }

    }

    @Override
    public void getMoviePopular(String token, String language, int pager, Contractor.OnMoviesLoadedListener listener) {
        PageMovie pageMovie = databaseRepository.getPopularPage(pager);
        if (pageMovie != null){
            listener.onLoadMoviesSuccess(pageMovie);
        } else {
            networkRepository.getMoviePopular(token, language, pager).subscribe(page -> {
                        listener.onLoadMoviesSuccess(page);
                        databaseRepository.insertPagePopular(page);
                    },
                    throwable -> listener.onLoadMoviesError(throwable.toString()));
        }

    }
}
