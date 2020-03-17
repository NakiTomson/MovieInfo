package com.example.dmiryz.ryzhov.movieinfo.domain.repositories

import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movie.MovieResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProvider
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.SORT_BY_POPULARITY
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.TV_TYPE
import com.example.dmiryz.ryzhov.movieinfo.domain.converters.MovieConverter

import kotlinx.coroutines.*
import java.lang.Exception

class MovieRepository(val movieConverter: MovieConverter, val movieProvider: MovieProvider) : IMovieRepository {

    override suspend fun getMoviePopular(page: Int): Deferred<List<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity>>{
        return try {
            val movies = movieProvider.getMovieListPopularity(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    override suspend fun getMovieRated(page: Int): Deferred<List<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity>>{
        return try {
            val movies = movieProvider.getMovieListTopRated(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    override suspend fun getMovieTv(page: Int): Deferred<List<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity>>{
        return try {
            val movies = movieProvider.getTVList(page).await()
            GlobalScope.async {
                movies.results!!.map { movie -> movieConverter.fromDateTvToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }



    override suspend fun getMovie(page: Int,movieType:String,gender:String,sortMovieType:String): Deferred<List<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity>>{
        return try {
            val movies = movieProvider.getMovie(page,movieType,gender,sortMovieType).await()
            GlobalScope.async {
                when(movieType){
                    TV_TYPE -> movies.results!!.map { movie -> movieConverter.fromDateTvToUI(model = movie) }
                    else -> movies.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
                }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    override suspend fun getMovieCategory(page: Int,movieType:String): Deferred<List<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity>>{
        return try {
            val movieResponseList:MutableList<MovieResponse> = ArrayList()
            com.example.dmiryz.ryzhov.movieinfo.domain.enums.MovieCategory.values().forEach { movieResponseList.add(movieProvider.getMovie(page,movieType,it.name, SORT_BY_POPULARITY).await()) }
            GlobalScope.async {
                when(movieType){
                    else -> movieResponseList.map { respons -> movieConverter.fromDateCategoryResponsToUICategeoryEntety(model = respons) }
//                    TV_TYPE -> movies.results!!.map { movie -> movieConverter.fromDateTvToUI(model = movie) }
                }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

}