package com.example.dmiryz.ryzhov.domain.converters


import android.app.Application
import com.example.dmiryz.ryzhov.data.remote.models.movie.MovieResponse
import com.example.dmiryz.ryzhov.data.remote.models.movie.MovieResult
import com.example.dmiryz.ryzhov.data.utils.Configs
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.BASE_POSTER_URL
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.BIG_POSTER_SIZE
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.MOVIE_TYPE
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.SMALL_POSTER_SIZE
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.TV_TYPE
import com.example.dmiryz.ryzhov.domain.R
import com.example.dmiryz.ryzhov.domain.converters.factory.Converter
import com.example.dmiryz.ryzhov.domain.enums.MovieCategory
import com.example.dmiryz.ryzhov.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity

class MovieConverter : Converter() {

    override fun fromDateMovieToUI(model: MovieResult): MovieEntity {
        return MovieEntity(
            title = model.title!!,
            year = model.releaseDate!!,
            posterPath = BASE_POSTER_URL + SMALL_POSTER_SIZE + model.posterPath!!,
            posterBackDropPath = BASE_POSTER_URL + BIG_POSTER_SIZE + model.backdropPath!!,
            voteCount = model.voteCount,
            voteAverage = model.voteAverage,
            overview = model.overview!!,
            id = model.id,
            typeMovie = MOVIE_TYPE
        )
    }

    override fun fromDateTvToUI(model: MovieResult): MovieEntity {
        return MovieEntity(
            title = model.name!!,
            year = model.firstAirDate!!,
            posterPath = BASE_POSTER_URL + SMALL_POSTER_SIZE + model.posterPath!!,
            posterBackDropPath = BASE_POSTER_URL + BIG_POSTER_SIZE + model.backdropPath!!,
            voteCount = model.voteCount,
            voteAverage = model.voteAverage,
            overview = model.overview!!,
            id = model.id,
            typeMovie = TV_TYPE
        )
    }

    fun fromDateCategoryResponsToUICategeoryEntety(model: MovieResponse): MovieCategoryEntity {

        return MovieCategoryEntity(
            categoryMovie = MovieCategory.valueOf(model.results?.get(0)?.genreIds?.get(0).toString()).title,
            movies = model.results!!.map { movieResult ->fromDateMovieToUI(model = movieResult) }
        )
    }
}