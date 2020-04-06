package com.example.dmiryz.ryzhov.movieinfo.data.remote.providers

import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movie.MovieResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movieImages.ImageMovieResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.review.ReviewResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.trailer.TrailerResponse
import kotlinx.coroutines.Deferred

interface MovieProvider {

    fun getMovieListPopularity(page: Int): Deferred<MovieResponse>

    fun getMovieListTopRated(page: Int): Deferred<MovieResponse>

    fun getTVList(page: Int): Deferred<MovieResponse>

    fun getDetailMovie(id: Int): Deferred<ResponseDetails>

    fun getTrailerMovie(id: Int): Deferred<TrailerResponse>

    fun getReviewMovie(id: Int): Deferred<ReviewResponse>

    fun getMovie(page: Int,movieType:String,gender:String,sortMovieType:String): Deferred<MovieResponse>


    fun getImageMovie(id: Int): Deferred<ImageMovieResponse>

    fun getSimilarMovie(id: Int): Deferred<MovieResponse>

}