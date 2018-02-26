package ru.helen.movie.feature.detailmovie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.inject.Named;

import ru.helen.movie.App;
import ru.helen.movie.R;
import ru.helen.movie.di.DetailModule;
import ru.helen.movie.model.DetailMovie;

public class DetailFragment extends Fragment implements Contractor.ViewDetail {
    private final static String ID = "id";
    public static final String URL_IMAGE = "https://image.tmdb.org/t/p/w185_and_h278_bestv2";
    @Inject
    Presenter presenter;

    @Inject
    @Named ("token") String token;

    @Inject
    @Named("language") String language;

    public DetailFragment() {
        // Required empty public constructor
    }


    public static DetailFragment newInstance(int id) {

        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ID, id);
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
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.getInstance().initDetailComponent(this).inject(this);

        int id = 0;
        if (getArguments() != null && getArguments().containsKey(ID)) {
            id = getArguments().getInt(ID);
        }
        if (id != 0){
            presenter.getMovieDetail(id, token, language);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getInstance().destroyDetailComponent();
    }

    @Override
    public void onShowDetailMovie(DetailMovie detail) {
        ImageView poster = getActivity().findViewById(R.id.imDetailMovie);
        Picasso.with(getActivity())
                .load(URL_IMAGE + detail.getPosterPath())
                .placeholder(R.drawable.progress_animation)
                .into(poster);
        TextView title = getActivity().findViewById(R.id.tvTitle);
        title.setText(detail.getTitle());

        SimpleDateFormat fmtOut = new SimpleDateFormat("dd.MM.yyyy");
        TextView date = getActivity().findViewById(R.id.date);
        date.setText(fmtOut.format(detail.getReleaseDate()));

        TextView popular = getActivity().findViewById(R.id.popular);
        popular.setText(String.valueOf(detail.getPopularity()));

        TextView description = getActivity().findViewById(R.id.description);
        description.setText(detail.getOverview());

    }
}
