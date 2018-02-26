package ru.helen.movie.di;

import dagger.Subcomponent;
import ru.helen.movie.feature.main.MainActivity;

/**
 * Subcomponent for MainActivity
 */
@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
