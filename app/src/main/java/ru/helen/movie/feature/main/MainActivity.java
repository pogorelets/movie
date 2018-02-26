package ru.helen.movie.feature.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.helen.movie.App;
import ru.helen.movie.R;
import ru.helen.movie.feature.movie.MovieAdapter;
import ru.helen.movie.feature.movie.MovieFragment;
import ru.helen.movie.model.Movie;

public class MainActivity extends AppCompatActivity implements Contractor.ViewMain{


    @Inject
    Presenter presenter;

    public final static String POPULAR = "popular";
    public final static String TOP = "top";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    showFragmentMovie(POPULAR);
                    return true;
                case R.id.navigation_dashboard:

                    showFragmentMovie(TOP);
                     return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().initMainComponent(this).inject(this);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showFragmentMovie(POPULAR);



    }

    private void showFragmentMovie(String typeMovie){
        FragmentTransaction ft;
        MovieFragment movieFragment = MovieFragment.newInstance(typeMovie);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fragment, movieFragment);
        ft.disallowAddToBackStack();
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().destroyMainComponent();
    }


}
