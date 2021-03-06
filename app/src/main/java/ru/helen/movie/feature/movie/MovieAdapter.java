package ru.helen.movie.feature.movie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.helen.movie.R;
import ru.helen.movie.model.Movie;

/**
 * Created by lenap on 23.02.2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movies = new ArrayList<>();
    private final static String PATH = "https://image.tmdb.org/t/p/w185_and_h278_bestv2";

    private Contractor.OnMovieClick listener;

    public MovieAdapter(Context context, Contractor.OnMovieClick listener) {
        this.context = context;
        this.listener = listener;

    }



    public void updateMovie(List<Movie> movies){
       // this.movies = movies;
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public  MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         Picasso.with(context)
                    .load(PATH + movies.get(position).getPosterPath())
                    .placeholder(context.getResources().getDrawable(R.drawable.ic_image_black_24dp) )
                    .into(holder.imageView);



        holder.imageView.setOnClickListener(v -> listener.onShowMovieDetail(movies.get(position)));


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imMovie);
        }
    }
}
