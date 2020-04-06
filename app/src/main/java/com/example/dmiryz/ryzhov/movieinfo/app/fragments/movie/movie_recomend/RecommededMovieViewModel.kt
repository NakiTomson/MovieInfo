package com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.movie_recomend

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.dmiryz.ryzhov.movieinfo.app.AppMovie

import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.IMovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RecommededMovieViewModel(app:Application) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()

    init {
        AppMovie.appComponent.inject(viewModel = this)
    }

    @Inject
    lateinit var moviesRepositoryImpl:IMovieRepository
    var moviePopular: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var movieRated: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var seriesTv: MutableLiveData<List<MovieEntity>> = MutableLiveData()



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


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
