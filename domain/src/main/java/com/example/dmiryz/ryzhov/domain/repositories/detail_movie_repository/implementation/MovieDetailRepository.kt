package com.example.dmiryz.ryzhov.domain.repositories.detail_movie_repository.implementation

import com.example.dmiryz.ryzhov.data.remote.providers.MovieProviderImpl
import com.example.dmiryz.ryzhov.domain.converters.TraillerConverter
import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.models.MovieTraillerEntity
import com.example.dmiryz.ryzhov.domain.repositories.detail_movie_repository.DetailMovieRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception


class MovieDetailRepository(val movieConverter: TraillerConverter): DetailMovieRepository {

    val movieProvider: MovieProviderImpl = MovieProviderImpl()

    override suspend fun getMovieDetail(id: Int): Deferred<MovieDetailEntity> {
        return try {
            val movieDetail = movieProvider.getDetailMovie(id).await()
            GlobalScope.async {
                movieConverter.fromDetailDateMovieToUI(detail = movieDetail)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun getReviewMovie(id: Int): Deferred<List<MovieReviewEntity>?> {
        return try {
            val movieReview = movieProvider.getReviewMovie(id).await()
            GlobalScope.async {
                movieReview.results?.map { review -> movieConverter.fromReviewDateMovieToUI(review = review) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    override suspend fun getTraillerMovie(id: Int): Deferred<MovieTraillerEntity?> {
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