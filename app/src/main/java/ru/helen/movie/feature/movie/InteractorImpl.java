package ru.helen.movie.feature.movie;

import ru.helen.movie.repository.NetworkRepository;

/**
 * Interactor for feature MovieFragment
 */

public class InteractorImpl implements Contractor.Interactor {
    private NetworkRepository networkRepository;

    public InteractorImpl(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    @Override
    public void getMovieTopRated(String token, String language, int pager, Contractor.OnMoviesLoadedListener listener) {
        networkRepository.getMovieTopRated(token, language, pager).subscribe(page -> {
                    listener.onLoadMoviesSuccess(page.getResults());
                },
                throwable -> listener.onLoadMoviesError(throwable.toString()));
    }

    @Override
    public void getMoviePopular(String token, String language, int pager, Contractor.OnMoviesLoadedListener listener) {
        networkRepository.getMoviePopular(token, language, pager).subscribe(page -> {
                    listener.onLoadMoviesSuccess(page.getResults());
                },
                throwable -> listener.onLoadMoviesError(throwable.toString()));
    }
}
