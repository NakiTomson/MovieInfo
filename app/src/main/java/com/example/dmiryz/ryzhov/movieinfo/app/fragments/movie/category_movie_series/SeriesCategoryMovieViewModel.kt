package com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.category_movie_series

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmiryz.ryzhov.movieinfo.app.AppMovie
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.repositories.IMovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SeriesCategoryMovieViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job: Job = Job()

    init {
        AppMovie.appComponent.inject(viewModel = this)
    }

    @Inject
    lateinit var moviesRepositoryImpl: IMovieRepository


    var seriesAllCategory: MutableLiveData<List<MovieCategoryEntity>> = MutableLiveData()


    fun getAllCategoryMovie() {
        launch(Dispatchers.Default) {
            try {
                val categoryWithMovie = moviesRepositoryImpl.getMovieCategory(1,"tv").await() //TODO tv?
                withContext(Dispatchers.Main){
                    seriesAllCategory.value = categoryWithMovie
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
