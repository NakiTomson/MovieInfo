package com.example.dmiryz.ryzhov.movieinfo.app.fragments.detail_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.AppMovie
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieTraillerEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.IDetailMovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DetailMovieViewModel : ViewModel(), CoroutineScope {


    @Inject
    lateinit var heroesRepositoryImpl: IDetailMovieRepository

    init {
        AppMovie.appComponent.inject(viewModel = this)
    }

    var movieDetail: MutableLiveData<MovieDetailEntity> = MutableLiveData()
    var movieRevies: MutableLiveData<List<MovieReviewEntity>> = MutableLiveData()
    var movieTrailler: MutableLiveData<MovieTraillerEntity> = MutableLiveData()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()


    fun getDetailsMovie(id: Int) {
        launch(Dispatchers.IO) {
            try {
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
