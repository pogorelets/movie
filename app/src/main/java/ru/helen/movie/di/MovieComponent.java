package ru.helen.movie.di;

import dagger.Subcomponent;
import ru.helen.movie.feature.movie.MovieFragment;

/**
 * Subcomponent for MovieFragment.
 */
@MovieScope
@Subcomponent(modules = {MovieModule.class})
public interface MovieComponent {
    void inject(MovieFragment fragment);
}
