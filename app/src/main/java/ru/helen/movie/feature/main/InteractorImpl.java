package ru.helen.movie.feature.main;

import ru.helen.movie.repository.NetworkRepository;

/**
 * Interactor for feature Main
 */

public class InteractorImpl implements Contractor.Interactor {
    private NetworkRepository networkRepository;

    public InteractorImpl(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

}
