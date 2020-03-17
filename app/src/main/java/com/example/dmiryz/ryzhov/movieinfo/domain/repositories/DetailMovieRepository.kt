package com.example.dmiryz.ryzhov.movieinfo.domain.repositories

import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProvider
import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProviderImpl
import com.example.dmiryz.ryzhov.movieinfo.domain.converters.TraillerConverter
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception


class DetailMovieRepository(val movieConverter: TraillerConverter,val movieProvider: MovieProvider):
    IDetailMovieRepository {


    override suspend fun getMovieDetail(id: Int): Deferred<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieDetailEntity> {
        return try {
            val movieDetail = movieProvider.getDetailMovie(id).await()
            GlobalScope.async {
                movieConverter.fromDetailDateMovieToUI(detail = movieDetail)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun getReviewMovie(id: Int): Deferred<List<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieReviewEntity>?> {
        return try {
            val movieReview = movieProvider.getReviewMovie(id).await()
            GlobalScope.async {
                movieReview.results?.map { review -> movieConverter.fromReviewDateMovieToUI(review = review) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


    override suspend fun getTraillerMovie(id: Int): Deferred<com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieTraillerEntity?> {
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