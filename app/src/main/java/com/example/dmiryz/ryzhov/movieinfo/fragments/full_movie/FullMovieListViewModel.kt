package com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmiryz.ryzhov.domain.converters.MovieConverter
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.domain.repositories.movie_repository.implementation.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FullMovieListViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()

    private val moviesRepositoryImpl = MovieRepository(movieConverter = MovieConverter())
    var movie: MutableLiveData<List<MovieEntity>> = MutableLiveData()

    fun getMovie(page :Int,movieType:String,gender:String) {
        launch(Dispatchers.Default) {
            try {
                val movies = moviesRepositoryImpl.getMovie(page,movieType,gender).await()
                withContext(Dispatchers.Main){
                    movie.value = movies
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
