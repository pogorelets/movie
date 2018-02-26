package ru.helen.movie.feature.detailmovie;

import ru.helen.movie.repository.NetworkRepository;

/**
 * Interactor for feature DetailFragment
 */

public class InteractorImpl implements Contractor.Interactor {
    private NetworkRepository networkRepository;

    public InteractorImpl(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    @Override
    public void getDetailMovie(int id, String token, String language, Contractor.OnLoadDetail listener) {
        networkRepository.getDetailMovie(id, token, language).subscribe(detailMovie -> {
                    listener.onSuccessLoadDetail(detailMovie);
                },
                throwable -> {
                    listener.onErrorLoadDetail(throwable.toString());
                }

        );
    }
}
