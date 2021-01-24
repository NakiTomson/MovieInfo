package com.example.dmiryz.ryzhov.movieinfo.data.remote.providers

import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movie.MovieResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.review.ReviewResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.trailer.TrailerResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.helpers.RetrofitFactory
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.API_KEY
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.LANGUAGE
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.MOVIE_TYPE
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.SORT_BY_POPULARITY
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.SORT_BY_RATED
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.TV_TYPE
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.VOTE_COUNT
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movieImages.ImageMovieResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.services.MovieServices
import kotlinx.coroutines.Deferred


class MovieProviderImpl(val movieServicesApi: MovieServices) :MovieProvider {

    override fun getMovieListPopularity(page: Int): Deferred<MovieResponse> {
        return movieServicesApi.getMovies(MOVIE_TYPE, API_KEY, LANGUAGE, SORT_BY_POPULARITY, page, VOTE_COUNT)
    }

    override fun getMovieListTopRated(page: Int): Deferred<MovieResponse> {
        return movieServicesApi.getMovies(MOVIE_TYPE, API_KEY, LANGUAGE, SORT_BY_RATED, page, VOTE_COUNT)
    }

    override fun getTVList(page: Int): Deferred<MovieResponse>{
        return movieServicesApi.getMovies(TV_TYPE, API_KEY, LANGUAGE, SORT_BY_POPULARITY, page, VOTE_COUNT)
    }

    override fun getDetailMovie(id: Int): Deferred<ResponseDetails> {
        return movieServicesApi.getDetails(id, API_KEY, LANGUAGE)
    }

    override fun getTrailerMovie(id: Int): Deferred<TrailerResponse> {
        return movieServicesApi.getTrailers(id, API_KEY, LANGUAGE)
    }

    override fun getReviewMovie(id: Int): Deferred<ReviewResponse> {
        return movieServicesApi.getReview(id, API_KEY, LANGUAGE, 1)
    }


    override fun getMovie(page: Int,movieType:String,gender:String,sortMovieType:String): Deferred<MovieResponse> {
        return movieServicesApi.getMoviesAll(movieType, API_KEY, LANGUAGE, sortMovieType, page, VOTE_COUNT,gender)
    }

    override fun getImageMovie(id: Int): Deferred<ImageMovieResponse> {
        return movieServicesApi.getMoviesImageById(id, API_KEY)
    }

    override fun getSimilarMovie(id: Int): Deferred<MovieResponse> {
        return movieServicesApi.getSimiliarMovies(id, API_KEY, LANGUAGE,1) //TODO page need to change
    }


}