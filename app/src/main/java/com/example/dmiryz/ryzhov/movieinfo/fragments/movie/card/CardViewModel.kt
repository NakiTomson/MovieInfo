package com.example.dmiryz.ryzhov.movieinfo.fragments.movie.card

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.dmiryz.ryzhov.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.domain.repositories.movie_repository.implementation.MovieRepository
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CardViewModel(app:Application) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()


    private val moviesRepositoryImpl = MovieRepository(movieConverter = MovieConverter())

    var moviePopular: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var movieRated: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var seriesTv: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var allCategoryMovie: MutableLiveData<List<MovieCategoryEntity>> = MutableLiveData()



    fun getMoviePopular() {
        launch(Dispatchers.Default) {
            try {
                val movies = moviesRepositoryImpl.getMoviePopular(1).await()
                withContext(Dispatchers.Main){
                    moviePopular.value = movies
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getMovieRated() {
        launch(Dispatchers.Default) {
            try {
                val movies = moviesRepositoryImpl.getMovieRated(1).await()
                withContext(Dispatchers.Main){
                    movieRated.value = movies
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getMovieTv() {
        launch(Dispatchers.Default) {
            try {
                val TVs = moviesRepositoryImpl.getMovieTv(1).await()
                withContext(Dispatchers.Main){
                    seriesTv.value = TVs
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getAllCategoryMovie() {
        launch(Dispatchers.Default) {
            try {
                val categoryWithMovie = moviesRepositoryImpl.getMovieCategory(1,"movie").await()
                withContext(Dispatchers.Main){
                    allCategoryMovie.value = categoryWithMovie
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
