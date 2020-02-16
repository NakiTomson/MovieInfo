package com.example.dmiryz.ryzhov.data.remote.services

import com.example.dmiryz.ryzhov.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.data.remote.models.movie.MovieResponse
import com.example.dmiryz.ryzhov.data.remote.models.review.ReviewResponse
import com.example.dmiryz.ryzhov.data.remote.models.trailer.TrailerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {

    @GET("/3/discover/{category}?")
    fun getMovies(
        @Path("category") movie: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int,
        @Query("vote_count.gte") vote_count: Int
    ): Deferred<MovieResponse>

    @GET("/3/movie/{id}/reviews?")
    fun getReview(
        @Path("id") id:Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<ReviewResponse>

    @GET("/3/movie/{id}?")
    fun getDetails(
        @Path("id") id:Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Deferred<ResponseDetails>


    @GET("/3/movie/{id}/videos?")
    fun getTrailers(
        @Path("id") id:Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Deferred<TrailerResponse>
}