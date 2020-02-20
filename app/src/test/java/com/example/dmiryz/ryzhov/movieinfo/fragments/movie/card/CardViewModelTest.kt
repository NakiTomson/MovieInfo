package com.example.dmiryz.ryzhov.movieinfo.fragments.movie.card

import com.example.dmiryz.ryzhov.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.domain.repositories.movie_repository.implementation.MovieRepository
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import kotlin.coroutines.CoroutineContext

class CardViewModelTest : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()

    private lateinit var moviesRepositoryImpl: MovieRepository

    @Before
    fun getRepository() {
        moviesRepositoryImpl = MovieRepository(movieConverter = MovieConverter())
    }

    @Test
    fun getMoviePopular() {
        launch(Dispatchers.Default) {
            val movies = moviesRepositoryImpl.getMoviePopular(10).await()
            Assert.assertTrue(movies[0].title.contains("a"))
        }

    }
}