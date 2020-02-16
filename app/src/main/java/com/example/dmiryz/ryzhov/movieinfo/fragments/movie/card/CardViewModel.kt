package com.example.dmiryz.ryzhov.movieinfo.fragments.movie.card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.dmiryz.ryzhov.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.domain.repositories.implementation.MovieRepository
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieEntity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CardViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()


    private val heroesRepositoryImpl = MovieRepository(movieConverter = MovieConverter())

    var moviePopular: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var movieRated: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var seriesTv: MutableLiveData<List<MovieEntity>> = MutableLiveData()


    fun getMoviePopular() {
        launch(Dispatchers.Default) {
            try {
                val movies = heroesRepositoryImpl.getMoviePopular(1).await()
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
                val movies = heroesRepositoryImpl.getMovieRated(1).await()
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
                val TVs = heroesRepositoryImpl.getMovieTv(1).await()
                withContext(Dispatchers.Main){
                    seriesTv.value = TVs
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
