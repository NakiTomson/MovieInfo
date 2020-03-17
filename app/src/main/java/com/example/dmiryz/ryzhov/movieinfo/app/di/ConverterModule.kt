package com.example.dmiryz.ryzhov.movieinfo.app.di

import com.example.dmiryz.ryzhov.movieinfo.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.movieinfo.domain.converters.TraillerConverter
import dagger.Module
import dagger.Provides

@Module
class ConverterModule {

    @Provides
    fun provideMovieConverter(): MovieConverter {
        return MovieConverter()
    }

    @Provides
    fun provideDetailConverter(): TraillerConverter {
        return TraillerConverter()
    }
}