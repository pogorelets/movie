package ru.helen.movie.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for Main
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface MainScope {
}
