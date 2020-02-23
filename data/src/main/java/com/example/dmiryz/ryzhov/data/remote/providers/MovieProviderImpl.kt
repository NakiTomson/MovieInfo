package com.example.dmiryz.ryzhov.data.remote.providers

import com.example.dmiryz.ryzhov.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.data.remote.models.movie.MovieResponse
import com.example.dmiryz.ryzhov.data.remote.models.review.ReviewResponse
import com.example.dmiryz.ryzhov.data.remote.models.trailer.TrailerResponse
import com.example.dmiryz.ryzhov.data.remote.helpers.RetrofitFactory
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.API_KEY
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.LANGUAGE
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.MOVIE_TYPE
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.SORT_BY_POPULARITY
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.SORT_BY_RATED
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.TV_TYPE
import com.example.dmiryz.ryzhov.data.utils.Configs.Companion.VOTE_COUNT
import kotlinx.coroutines.Deferred


class MovieProviderImpl :MovieProvider {

    override fun getMovieListPopularity(page: Int): Deferred<MovieResponse> {
        return RetrofitFactory.getMovieService().getMovies(MOVIE_TYPE, API_KEY, LANGUAGE, SORT_BY_POPULARITY, page, VOTE_COUNT)
    }

    override fun getMovieListTopRated(page: Int): Deferred<MovieResponse> {
        return RetrofitFactory.getMovieService().getMovies(MOVIE_TYPE, API_KEY, LANGUAGE, SORT_BY_RATED, page, VOTE_COUNT)
    }

    override fun getTVList(page: Int): Deferred<MovieResponse>{
        return RetrofitFactory.getMovieService()
            .getMovies(TV_TYPE, API_KEY, LANGUAGE, SORT_BY_POPULARITY, page, VOTE_COUNT)
    }

    override fun getDetailMovie(id: Int): Deferred<ResponseDetails> {
        return RetrofitFactory.getMovieService().getDetails(id, API_KEY, LANGUAGE)
    }

    override fun getTrailerMovie(id: Int): Deferred<TrailerResponse> {
        return RetrofitFactory.getMovieService().getTrailers(id, API_KEY, LANGUAGE)
    }

    override fun getReviewMovie(id: Int): Deferred<ReviewResponse> {
        return RetrofitFactory.getMovieService().getReview(id, API_KEY, LANGUAGE, 1)
    }


    override fun getMovie(page: Int,movieType:String,gender:String,sortMovieType:String): Deferred<MovieResponse> {
        return RetrofitFactory.getMovieService().getMoviesAll(movieType, API_KEY, LANGUAGE, sortMovieType, page, VOTE_COUNT,gender)
    }
}