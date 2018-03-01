package ru.helen.movie.feature.movie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.helen.movie.App;
import ru.helen.movie.R;
import ru.helen.movie.feature.detailmovie.DetailFragment;
import ru.helen.movie.model.Movie;

import static ru.helen.movie.feature.main.MainActivity.POPULAR;
import static ru.helen.movie.feature.main.MainActivity.TOP;


public class MovieFragment extends Fragment implements Contractor.ViewTopMovie, Contractor.OnMovieClick {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private MovieAdapter adapter;
    @Inject
    Presenter presenter;

    @Inject
    @Named ("token") String token;

    @Inject
    @Named ("language") String language;

    private final static String TYPEMOVIE = "typemovie";
    private String flag = POPULAR;
    private int pageall = 1;
    private int pagetop = 1;
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int totalItemCount = gridLayoutManager.getItemCount();
            int spanItemCount = gridLayoutManager.getSpanCount();
            int lastVisibleItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
            if (totalItemCount - lastVisibleItem <= spanItemCount){
                if (flag.equals(POPULAR)) {
                    pageall = pageall + 1;
                    presenter.getAllMovies(token, language, pageall);
                }
                if (flag.equals(TOP))    {
                    pagetop = pagetop + 1;
                    presenter.getTopMovie(token,language, pagetop);
                }

            }
        }
    };

    public MovieFragment() {
        // Required empty public constructor
    }


    public static MovieFragment newInstance(String typemovie) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(TYPEMOVIE, typemovie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.getInstance().initTopMovieComponent(this).inject(this);
        String typemovie = POPULAR;
        if (getArguments() != null && getArguments().containsKey(TYPEMOVIE)) {
            typemovie = getArguments().getString(TYPEMOVIE);
        }

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new MovieAdapter(getActivity(),new ArrayList<>(), this);
        gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(scrollListener);

        switch (typemovie){
            case POPULAR: presenter.getAllMovies(token, language, pageall);
                flag = POPULAR;
                break;

            case TOP: presenter.getTopMovie(token, language, pagetop);
                flag = TOP;
                break;

//            default: presenter.getAllMovies(token, language, pageall);
//                flag = POPULAR;
//                break;

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getInstance().destroyTopMovieComponent();
    }

    @Override
    public void updateAdapter(List<Movie> movies) {
        adapter.updateMovie(movies);
    }

    @Override
    public void onShowMovieDetail(Movie movie) {
       showFragmentDetail(movie.getId());
    }

    private void showFragmentDetail(int id){
        FragmentTransaction ft;
        DetailFragment detailFragment = DetailFragment.newInstance(id);
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fragment, detailFragment);
        ft.disallowAddToBackStack();
        ft.commit();
    }
}
