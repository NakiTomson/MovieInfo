package com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.card

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmiryz.ryzhov.movieinfo.app.App
import com.example.dmiryz.ryzhov.movieinfo.data.remote.providers.MovieProviderImpl

import com.example.dmiryz.ryzhov.movieinfo.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.MovieRepository
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.IMovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CardViewModel(app:Application) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()

    init {
        App.appComponent.inject(viewModel = this)
    }

    @Inject
    lateinit var moviesRepositoryImpl:IMovieRepository


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
