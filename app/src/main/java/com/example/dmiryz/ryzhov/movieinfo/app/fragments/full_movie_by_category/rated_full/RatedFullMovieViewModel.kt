package com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.rated_full

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.AppMovie
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.IMovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RatedFullMovieViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()

    init {
        AppMovie.appComponent.inject(viewModel = this)
    }

    @Inject
    lateinit var moviesRepositoryImpl: IMovieRepository

    var movieRatedList: MutableLiveData<List<MovieEntity>> = MutableLiveData()

    fun getMovieRated(page :Int,movieType:String,gender:String,typeSorted:String) {
        launch(Dispatchers.Default) {
            try {
                val movies = moviesRepositoryImpl.getMovie(page,movieType,gender,typeSorted).await()
                withContext(Dispatchers.Main){
                    if (movies.isEmpty())return@withContext
                    movieRatedList.value = movies
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
