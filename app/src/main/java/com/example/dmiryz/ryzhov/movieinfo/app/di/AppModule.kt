package com.example.dmiryz.ryzhov.movieinfo.app.di

import android.content.Context
import com.example.dmiryz.ryzhov.movieinfo.app.AppMovie
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: AppMovie) {

    @Provides @Singleton
    fun provideContext(): Context = app

}