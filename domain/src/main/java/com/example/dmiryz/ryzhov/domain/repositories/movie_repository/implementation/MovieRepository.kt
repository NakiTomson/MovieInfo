package com.example.dmiryz.ryzhov.domain.repositories.movie_repository.implementation

import com.example.dmiryz.ryzhov.data.remote.providers.MovieProviderImpl
import com.example.dmiryz.ryzhov.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.domain.converters.factory.Converter
import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.models.MovieTraillerEntity
import com.example.dmiryz.ryzhov.domain.repositories.movie_repository.MovieRepository

import kotlinx.coroutines.*
import java.lang.Exception

class MovieRepository(val movieConverter: MovieConverter) : MovieRepository {

    val movieProvider: MovieProviderImpl = MovieProviderImpl()

    override suspend fun getMoviePopular(page: Int): Deferred<List<MovieEntity>>{
        return try {
            val movies = movieProvider.getMovieListPopularity(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    override suspend fun getMovieRated(page: Int): Deferred<List<MovieEntity>>{
        return try {
            val movies = movieProvider.getMovieListTopRated(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    override suspend fun getMovieTv(page: Int): Deferred<List<MovieEntity>>{
        return try {
            val movies = movieProvider.getTVList(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateTvToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }




    override suspend fun getMovie(page: Int,movieType:String,gender:String): Deferred<List<MovieEntity>>{
        return try {
            val movies = movieProvider.getMovie(page,movieType,gender).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateTvToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


}