package com.example.dmiryz.ryzhov.movieinfo.app.di

import com.example.dmiryz.ryzhov.movieinfo.app.fragments.detail_movie.DetailMovieViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.popular_full.PopularFullMovieViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.rated_full.RatedFullMovieViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.category_movie_film.FilmsCategoryMovieViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.category_movie_series.SeriesCategoryMovieViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.movie_recomend.RecommededMovieViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class,RepositoryModule::class,ProvidersModule::class,ConverterModule::class,RestApiRetrofitModule::class])
@Singleton
interface AppComponent {

    fun inject(viewModel: RecommededMovieViewModel)
    fun inject(viewModel: FilmsCategoryMovieViewModel)
    fun inject(viewModel: SeriesCategoryMovieViewModel)

    fun inject(viewModel: PopularFullMovieViewModel)
    fun inject(viewModel: RatedFullMovieViewModel)
    fun inject(viewModel: DetailMovieViewModel)


}