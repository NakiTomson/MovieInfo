package com.example.dmiryz.ryzhov.movieinfo.app.di

import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProvider
import com.example.dmiryz.ryzhov.movieinfo.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.movieinfo.domain.converters.TraillerConverter
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.DetailMovieRepository
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.IDetailMovieRepository
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.IMovieRepository
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    fun provideMovieRepository(converter: MovieConverter,movieProvider: MovieProvider): IMovieRepository {
        return MovieRepository(movieConverter = converter,movieProvider = movieProvider)
    }

    @Provides
    fun provideMovieDetailRepository(converter: TraillerConverter,movieProvider: MovieProvider): IDetailMovieRepository {
        return DetailMovieRepository(movieConverter = converter,movieProvider = movieProvider)
    }

}