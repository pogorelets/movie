package ru.helen.movie.feature.movie;

import android.util.Log;

import ru.helen.movie.model.PageMovie;
import ru.helen.movie.repository.DatabaseRepository;
import ru.helen.movie.repository.NetworkRepository;

import static ru.helen.movie.repository.DatabaseRepositoryImpl.POPULAR;
import static ru.helen.movie.repository.DatabaseRepositoryImpl.TOP;

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
        if (pageMovie != null && pageMovie.getTotalPages() < pager) {
            if (pager != 1) {
                listener.onLoadMoviesSuccess(pageMovie);
            } else {
                networkRepository.getMovieTopRated(token, language, pager).subscribe(page -> {
                            page.setTypemovie(TOP);
                            if (pager == 1) {
                                //Проверка актуальности бд, такой вариант реализован для примера
                                //Проверка на совпадение название первого фильма на первой странице, так как при изменении
                                //рейтинга по идее изменится в первую очередь первый фильм
                                if (pageMovie != null && !pageMovie.getResults().get(0).getTitle().equals(page.getResults().get(0).getTitle())) {
                                    databaseRepository.clearAll();
                                }
                            }
                            listener.onLoadMoviesSuccess(page);
                            databaseRepository.insertPage(page);

                        },
                        throwable -> listener.onLoadMoviesError(throwable.toString()));
            }
        }

    }

    @Override
    public void getMoviePopular(String token, String language, int pager, Contractor.OnMoviesLoadedListener listener) {
        PageMovie pageMovie = databaseRepository.getPopularPage(pager);
        if (pageMovie != null && pageMovie.getTotalPages() < pager) {
            if (pager != 1) {
                listener.onLoadMoviesSuccess(pageMovie);
            } else {
                networkRepository.getMoviePopular(token, language, pager).subscribe(page -> {
                            page.setTypemovie(POPULAR);
                            //При загрузке первой страницы проверяем актуальность локальной бд
                            if (pager == 1) {
                                if (pageMovie != null && !pageMovie.getResults().get(0).getTitle().equals(page.getResults().get(0).getTitle())) {
                                    databaseRepository.clearAll();
                                }
                            }
                            listener.onLoadMoviesSuccess(page);
                            databaseRepository.insertPage(page);

                        },
                        throwable -> listener.onLoadMoviesError(throwable.toString()));
            }
        }
    }
}
