package ru.helen.movie.repository;

import ru.helen.movie.model.DetailMovie;
import ru.helen.movie.model.PageMovie;

/**
 * Interface for DatabaseRepository
 */

public interface DatabaseRepository {

    void insertPage(PageMovie page);

    void insertDetailMovie(DetailMovie detail);

    void clearAll();

    PageMovie getTopRatedPage(int page);

    PageMovie getPopularPage(int page);

    DetailMovie getDetailMovie(int id);

    void close();
}
