package com.example.dmiryz.ryzhov.domain.repositories.movie_repository

import com.example.dmiryz.ryzhov.domain.models.*
import kotlinx.coroutines.Deferred

interface MovieRepository {

    suspend fun getMoviePopular(page: Int): Deferred<List<MovieEntity>>
    suspend fun getMovieRated(page: Int): Deferred<List<MovieEntity>>
    suspend fun getMovieTv(page: Int): Deferred<List<MovieEntity>>
    suspend fun getMovie(page: Int,movieType:String,gender:String,sortMovieType:String): Deferred<List<MovieEntity>>
    suspend fun getMovieCategory(page: Int,movieType:String): Deferred<List<MovieCategoryEntity>>

//    suspend fun getMovieDetail(id: Int): Deferred<MovieDetailEntity>
//    suspend fun getReviewMovie(id: Int): Deferred<List<MovieReviewEntity>?>
//    suspend fun getTraillerMovie(id: Int): Deferred<MovieTraillerEntity?>

}