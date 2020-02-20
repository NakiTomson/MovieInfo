package com.example.dmiryz.ryzhov.domain.repositories.movie_repository.implementation

import com.example.dmiryz.ryzhov.data.remote.providers.MovieProviderImpl
import org.junit.Assert
import org.junit.Test

import org.junit.Before

class MovieRepositoryTest {

    lateinit var movieProvider: MovieProviderImpl

    @Before
    fun initProvider(){
        movieProvider = MovieProviderImpl()
    }

    @Test
    fun getMovieProvider() {

    }

    @Test
    fun getMoviePopular() {
        val movies = movieProvider.getMovieListPopularity(1)
        Assert.assertNotNull("movieProviderFucked",movies)
    }

    @Test
    fun getMovieRated() {
        val movies = movieProvider.getMovieListTopRated(1)
        Assert.assertNotNull("movieProviderFucked",movies)
    }

    @Test
    fun getMovieTv() {
        val movies = movieProvider.getTVList(1)
        Assert.assertNotNull("movieProviderFucked",movies)
    }

    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getReviewMovie() {
    }

    @Test
    fun getTraillerMovie() {
    }

    @Test
    fun getMovieConverter() {

    }

}