package com.example.dmiryz.ryzhov.movieinfo.domain.repositories

import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieTraillerEntity
import kotlinx.coroutines.Deferred

interface IDetailMovieRepository {

    suspend fun getMovieDetail(id: Int): Deferred<MovieDetailEntity>
    suspend fun getReviewMovie(id: Int): Deferred<List<MovieReviewEntity>?>
    suspend fun getTraillerMovie(id: Int): Deferred<MovieTraillerEntity?>

}