package com.example.dmiryz.ryzhov.domain.repositories.implementation

import com.example.dmiryz.ryzhov.data.remote.providers.MovieProviderImpl
import com.example.dmiryz.ryzhov.domain.converters.factory.Converter
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieEntity
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieTraillerEntity

import kotlinx.coroutines.*
import java.lang.Exception

class MovieRepository(val movieConverter: Converter) {

    val movieProvider: MovieProviderImpl = MovieProviderImpl()

    suspend fun getMoviePopular(page: Int): Deferred<List<MovieEntity>>{
        return try {
            val movies = movieProvider.getMovieListPopularity(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    suspend fun getMovieRated(page: Int): Deferred<List<MovieEntity>>{
        return try {
            val movies = movieProvider.getMovieListTopRated(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    suspend fun getMovieTv(page: Int): Deferred<List<MovieEntity>>{
        return try {
            val movies = movieProvider.getTVList(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateTvToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    suspend fun getMovieDetail(id: Int): Deferred<MovieDetailEntity>{
        return try {
            val movieDetail = movieProvider.getDetailMovie(id).await()
            GlobalScope.async {
                movieConverter.fromDetailDateMovieToUI(detail = movieDetail)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    suspend fun getReviewMovie(id: Int): Deferred<List<MovieReviewEntity>?>{
        return try {
            val movieReview = movieProvider.getReviewMovie(id).await()
            GlobalScope.async {
                movieReview.results?.map { review -> movieConverter.fromReviewDateMovieToUI(review = review) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    suspend fun getTraillerMovie(id: Int): Deferred<MovieTraillerEntity?>{
        return try {
            val moviewtrailler = movieProvider.getTrailerMovie(id).await()
            GlobalScope.async {
                moviewtrailler.results?.get(0)?.let { movieConverter.fromTraillerDateMovieToUI(trailler = it) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}