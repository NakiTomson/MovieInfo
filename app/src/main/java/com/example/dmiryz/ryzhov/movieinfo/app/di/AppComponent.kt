package com.example.dmiryz.ryzhov.movieinfo.app.di

import com.example.dmiryz.ryzhov.movieinfo.app.fragments.detail_movie.DetailMovieViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie.full_movie.FullMovieViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.card.CardViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class,RepositoryModule::class,ProvidersModule::class,ConverterModule::class])
@Singleton
interface AppComponent {

    fun inject(viewModel: CardViewModel)
    fun inject(viewModel: FullMovieViewModel)
    fun inject(viewModel: DetailMovieViewModel)

}