package com.example.dmiryz.ryzhov.movieinfo.data.remote.helpers

import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movie.MovieResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movieImages.ImageMovieResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.review.ReviewResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.trailer.TrailerResponse
import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProvider
import com.example.dmiryz.ryzhov.movieinfo.data.remote.services.MovieServices
import kotlinx.coroutines.Deferred



class ProxyProvider(val movieServicesApi: MovieServices): MovieProvider {

    override fun getMovieListPopularity(page: Int): Deferred<MovieResponse> {

        //TODO сделать подмену через proxy
        return movieServicesApi.getMovies(
            ProtocolState.MOVIE_TYPE.capital,
            Configs.API_KEY,
            Configs.LANGUAGE,
            Configs.SORT_BY_POPULARITY, page,
            Configs.VOTE_COUNT
        )
    }

    override fun getMovieListTopRated(page: Int): Deferred<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getTVList(page: Int): Deferred<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getDetailMovie(id: Int): Deferred<ResponseDetails> {
        TODO("Not yet implemented")
    }

    override fun getTrailerMovie(id: Int): Deferred<TrailerResponse> {
        TODO("Not yet implemented")
    }

    override fun getReviewMovie(id: Int): Deferred<ReviewResponse> {
        TODO("Not yet implemented")
    }

    override fun getMovie(page: Int, movieType: String, gender: String, sortMovieType: String): Deferred<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getImageMovie(id: Int): Deferred<ImageMovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getSimilarMovie(id: Int): Deferred<MovieResponse> {
        TODO("Not yet implemented")
    }

}