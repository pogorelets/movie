package ru.helen.movie.repository;

import io.realm.Realm;
import ru.helen.movie.model.DetailMovie;
import ru.helen.movie.model.PageMovie;

/**
 * DatabaseRepository
 */

public class DatabaseRepositoryImpl implements DatabaseRepository {
    private final Realm realm;
    private static final String TOP = "top";
    private static final String POPULAR = "popular";
    private static final String TYPEMOVIE = "typemovie";
    private static final String PAGE = "page";
    private static final String ID = "id";

    public DatabaseRepositoryImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insertPageTopRated(PageMovie page) {
        page.setTypemovie(TOP);
        insertPage(page);
    }

    @Override
    public void insertPagePopular(PageMovie page) {
        page.setTypemovie(POPULAR);
        insertPage(page);
    }

    private void insertPage(PageMovie page){
        realm.beginTransaction();
        realm.insert(page);
        realm.commitTransaction();
    }

    @Override
    public void insertDetailMovie(DetailMovie detail) {
        realm.beginTransaction();
        realm.insertOrUpdate(detail);
        realm.commitTransaction();
    }

    @Override
    public void clearAll() {
        realm.deleteAll();
    }

    @Override
    public PageMovie getTopRatedPage(int page) {
        return realm.where(PageMovie.class).equalTo(TYPEMOVIE,TOP).equalTo(PAGE,page).findFirst();
    }

    @Override
    public PageMovie getPopularPage(int page) {
        return realm.where(PageMovie.class).equalTo(TYPEMOVIE,POPULAR).equalTo(PAGE,page).findFirst();
    }

    @Override
    public DetailMovie getDetailMovie(int id) {
        return realm.where(DetailMovie.class).equalTo(ID, id).findFirst();
    }

    @Override
    public void close() {
        realm.close();
    }

}
