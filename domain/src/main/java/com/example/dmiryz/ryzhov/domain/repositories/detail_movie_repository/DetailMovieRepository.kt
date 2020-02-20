package com.example.dmiryz.ryzhov.domain.repositories.detail_movie_repository

import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.models.MovieTraillerEntity
import kotlinx.coroutines.Deferred

interface DetailMovieRepository {

    suspend fun getMovieDetail(id: Int): Deferred<MovieDetailEntity>
    suspend fun getReviewMovie(id: Int): Deferred<List<MovieReviewEntity>?>
    suspend fun getTraillerMovie(id: Int): Deferred<MovieTraillerEntity?>

}