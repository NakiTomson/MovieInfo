package com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie.full_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmiryz.ryzhov.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.domain.repositories.movie_repository.implementation.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FullMovieViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()


    private val moviesRepositoryImpl = MovieRepository(movieConverter = MovieConverter())
    var moviePopular: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var movieRated: MutableLiveData<List<MovieEntity>> = MutableLiveData()

    fun getMoviePopular(page :Int,movieType:String,gender:String,typeSorted:String) {
        launch(Dispatchers.Default) {
            try {
                val movies = moviesRepositoryImpl.getMovie(page,movieType,gender,typeSorted).await()
                withContext(Dispatchers.Main){
                    if (movies.isEmpty())return@withContext
                    moviePopular.value = movies
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovieRated(page :Int,movieType:String,gender:String,typeSorted:String) {
        launch(Dispatchers.Default) {
            try {
                val movies = moviesRepositoryImpl.getMovie(page,movieType,gender,typeSorted).await()
                withContext(Dispatchers.Main){
                    if (movies.isEmpty())return@withContext
                    movieRated.value = movies
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
