package com.example.dmiryz.ryzhov.movieinfo.fragments.detail_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmiryz.ryzhov.domain.converters.DetailMovieConverter
import com.example.dmiryz.ryzhov.domain.converters.ReviewConverter
import com.example.dmiryz.ryzhov.domain.converters.TraillerConverter
import com.example.dmiryz.ryzhov.domain.repositories.movie_repository.implementation.MovieRepository
import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.models.MovieTraillerEntity
import com.example.dmiryz.ryzhov.domain.repositories.detail_movie_repository.implementation.MovieDetailRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailMovieViewModel : ViewModel(), CoroutineScope {


    private lateinit var heroesRepositoryImpl:MovieDetailRepository
    var movieDetail: MutableLiveData<MovieDetailEntity> = MutableLiveData()
    var movieRevies: MutableLiveData<List<MovieReviewEntity>> = MutableLiveData()
    var movieTrailler: MutableLiveData<MovieTraillerEntity> = MutableLiveData()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()


    fun getDetailsMovie(id: Int) {
        launch(Dispatchers.IO) {
            try {
                heroesRepositoryImpl = MovieDetailRepository(movieConverter = TraillerConverter())
                val movieDetails = heroesRepositoryImpl.getMovieDetail(id).await()
                withContext(Dispatchers.Main){
                    movieDetail.value = movieDetails
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getReviewMovie(id: Int) {
        launch(Dispatchers.IO) {
            try {
                heroesRepositoryImpl = MovieDetailRepository(movieConverter = TraillerConverter())
                val reviews = heroesRepositoryImpl.getReviewMovie(id).await()
                withContext(Dispatchers.Main){
                    movieRevies.value = reviews
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getTrailer(id: Int) {
        launch(Dispatchers.IO) {
            try {
                heroesRepositoryImpl = MovieDetailRepository(movieConverter = TraillerConverter())
                val trailler = heroesRepositoryImpl.getTraillerMovie(id).await()
                withContext(Dispatchers.Main){
                    movieTrailler.value = trailler
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
