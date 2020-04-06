package com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.category_movie_film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.app.adapters.CategoryMovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.domain.enums.MovieCategory
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import kotlinx.android.synthetic.main.card_fragment.*
import kotlinx.android.synthetic.main.fimls_category_movie_fragment.*

class FilmsCategoryMovieFragment : Fragment() {

    companion object {
        fun newInstance() = FilmsCategoryMovieFragment()
    }

    private lateinit var filmCategoryViewModel: FilmsCategoryMovieViewModel
    lateinit var myCategoryAdapter: CategoryMovieAdapter
    lateinit var genders:List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fimls_category_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        filmCategoryViewModel = ViewModelProvider(this).get(FilmsCategoryMovieViewModel::class.java)
        genders = resources.getStringArray(R.array.genders).toList()
        downloadData()
    }

    private fun downloadData() {
        filmCategoryViewModel.getAllCategoryMovie()
        myCategoryAdapter = CategoryMovieAdapter()
        filmCategoryRecyclerView.layoutManager = GridLayoutManager(context,2)
        filmCategoryRecyclerView.adapter = myCategoryAdapter
        filmCategoryViewModel.movieAllCategory.observe(activity!!, Observer {
            val categoryMovie:MutableList<MovieCategoryEntity>  = ArrayList()
            for ((index, value) in it.withIndex()) {
                categoryMovie.add(
                    MovieCategoryEntity(categoryMovie = genders[index], movies = value.movies, gender = MovieCategory.values()[index].name)
                )
            }
            categoryMovie.forEach { movieCategory -> myCategoryAdapter.addMoviesCategory(movieCategory) }
        })
    }

}
