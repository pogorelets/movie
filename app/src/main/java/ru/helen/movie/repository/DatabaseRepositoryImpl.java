package ru.helen.movie.repository;

import io.realm.Realm;
import ru.helen.movie.model.DetailMovie;
import ru.helen.movie.model.PageMovie;

/**
 * DatabaseRepository
 */

public class DatabaseRepositoryImpl implements DatabaseRepository {
    private final Realm realm;
    public static final String TOP = "top";
    public static final String POPULAR = "popular";
    private static final String TYPEMOVIE = "typemovie";
    private static final String PAGE = "page";
    private static final String ID = "id";

    public DatabaseRepositoryImpl(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void insertPage(PageMovie page){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                page.setId(getId());
                realm.insertOrUpdate(page);
            }
        });
    }
    private int getId(){
        Number currentIdNum = realm.where(PageMovie.class).max("id");
        int nextId = currentIdNum == null ? 1 : currentIdNum.intValue() + 1;
        return nextId;
    }

    @Override
    public void insertDetailMovie(DetailMovie detail) {
        realm.beginTransaction();
        realm.insertOrUpdate(detail);
        realm.commitTransaction();
    }

    @Override
    public void clearAll() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

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
