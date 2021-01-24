package com.example.dmiryz.ryzhov.movieinfo.app.di

import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProvider
import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProviderImpl
import com.example.dmiryz.ryzhov.movieinfo.data.remote.services.MovieServices
import dagger.Module
import dagger.Provides

@Module
class ProvidersModule {

    @Provides
    fun provideMovieProviders(moveServiceApi: MovieServices): MovieProvider {
        return MovieProviderImpl(moveServiceApi)
    }

}