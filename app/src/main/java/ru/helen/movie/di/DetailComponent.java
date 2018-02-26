package ru.helen.movie.di;

import dagger.Subcomponent;
import ru.helen.movie.feature.detailmovie.DetailFragment;

/**
 * Subcomponent for DetailFragment
 */
@DetailScope
@Subcomponent(modules = {DetailModule.class})
public interface DetailComponent {
    void inject(DetailFragment fragment);
}
