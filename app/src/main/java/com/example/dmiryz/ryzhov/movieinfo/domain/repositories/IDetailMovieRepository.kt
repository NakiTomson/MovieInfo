package com.example.dmiryz.ryzhov.movieinfo.domain.repositories

import com.example.dmiryz.ryzhov.movieinfo.domain.models.*
import kotlinx.coroutines.Deferred

interface IDetailMovieRepository {

    suspend fun getMovieDetail(id: Int): Deferred<MovieDetailEntity>
    suspend fun getReviewMovie(id: Int): Deferred<List<MovieReviewEntity>?>
    suspend fun getTraillerMovie(id: Int): Deferred<MovieTraillerEntity?>
    suspend fun getMovieImage(id: Int): Deferred<List<ImageMovieEntity>?>
    suspend fun getSimilarMovies(id: Int): Deferred<List<MovieEntity>?>

}