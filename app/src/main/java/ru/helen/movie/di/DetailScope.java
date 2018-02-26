package ru.helen.movie.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for DetailFragment
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface DetailScope {
}
