package com.example.dmiryz.ryzhov.movieinfo.domain.repositories

import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProvider
import com.example.dmiryz.ryzhov.movieinfo.domain.converters.ImageMovieConverter
import com.example.dmiryz.ryzhov.movieinfo.domain.converters.TraillerConverter
import com.example.dmiryz.ryzhov.movieinfo.domain.models.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception


class DetailMovieRepositoryImpl(val movieConverter: ImageMovieConverter, val movieProvider: MovieProvider): IDetailMovieRepository {

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

    override suspend fun getMovieImage(id: Int): Deferred<List<ImageMovieEntity>?> {
        return try {
            val movieImageResult = movieProvider.getImageMovie(id).await()
            GlobalScope.async {
                movieImageResult.backdrops?.map { movieConverter.fromImageDateResponseToImageEntity(it) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun getSimilarMovies(id: Int): Deferred<List<MovieEntity>?> {
        return try {
            val movieImageResults = movieProvider.getSimilarMovie(id).await()
            GlobalScope.async {
                movieImageResults.results!!.map { movie -> movieConverter.fromDateMovieToUI(model = movie) }
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


}