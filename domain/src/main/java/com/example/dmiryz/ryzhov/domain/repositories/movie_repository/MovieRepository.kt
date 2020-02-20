package com.example.dmiryz.ryzhov.domain.repositories.movie_repository

import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.models.MovieTraillerEntity
import kotlinx.coroutines.Deferred

interface MovieRepository {

    suspend fun getMoviePopular(page: Int): Deferred<List<MovieEntity>>
    suspend fun getMovieRated(page: Int): Deferred<List<MovieEntity>>
    suspend fun getMovieTv(page: Int): Deferred<List<MovieEntity>>
    suspend fun getMovie(page: Int,movieType:String,gender:String): Deferred<List<MovieEntity>>

//    suspend fun getMovieDetail(id: Int): Deferred<MovieDetailEntity>
//    suspend fun getReviewMovie(id: Int): Deferred<List<MovieReviewEntity>?>
//    suspend fun getTraillerMovie(id: Int): Deferred<MovieTraillerEntity?>

}