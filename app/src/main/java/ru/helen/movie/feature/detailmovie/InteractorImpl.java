package ru.helen.movie.feature.detailmovie;

import ru.helen.movie.model.DetailMovie;
import ru.helen.movie.repository.DatabaseRepository;
import ru.helen.movie.repository.NetworkRepository;

/**
 * Interactor for feature DetailFragment
 */

public class InteractorImpl implements Contractor.Interactor {
    private NetworkRepository networkRepository;
    private DatabaseRepository databaseRepository;

    public InteractorImpl(NetworkRepository networkRepository, DatabaseRepository databaseRepository) {
        this.networkRepository = networkRepository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public void getDetailMovie(int id, String token, String language, Contractor.OnLoadDetail listener) {
        DetailMovie detail = databaseRepository.getDetailMovie(id);
        if (detail != null){
            listener.onSuccessLoadDetail(detail);
        } else {
            networkRepository.getDetailMovie(id, token, language).subscribe(detailMovie -> {
                        listener.onSuccessLoadDetail(detailMovie);
                        databaseRepository.insertDetailMovie(detailMovie);
                    },
                    throwable -> {
                        listener.onErrorLoadDetail(throwable.toString());
                    }

            );
        }

    }
}
