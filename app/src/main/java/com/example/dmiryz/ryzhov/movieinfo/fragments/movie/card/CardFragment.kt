package com.example.dmiryz.ryzhov.movieinfo.fragments.movie.card

import android.app.Application
import android.icu.text.CaseMap
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmiryz.ryzhov.domain.enums.MovieCategory
import com.example.dmiryz.ryzhov.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity

import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.adapters.CategoryMovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.adapters.MovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.fragments.movie.MovieSectionFragmentDirections
import com.example.dmiryz.ryzhov.movieinfo.utils.AppBarStateChangeListener
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.FragmentPosition
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.card_fragment.*

class CardFragment : Fragment() {

    private lateinit var movieViewModel: CardViewModel
    lateinit var myCategoryAdapter: CategoryMovieAdapter
    lateinit var genders:List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.card_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java)
        genders = resources.getStringArray(R.array.genders).toList()
        downloadData()

        myCategoryAdapter.movieSelectedListener = object : CategoryMovieAdapter.MovieSelectedListener {
            override fun onMovieSelected(movie: MovieEntity, imageView: ImageView,title:TextView) {
                val extras = FragmentNavigatorExtras(
                    imageView to movie.posterPath,
                    title to  movie.title
                )
                val action = MovieSectionFragmentDirections.actionNavMovieToDetailMovieFragment(
                    titleMovie = movie.title,
                    posterPath = movie.posterPath,
                    backdropPath = movie.posterBackDropPath,
                    yearStart = movie.year,
                    voteCount = movie.voteCount,
                    voteAverage = movie.voteAverage.toFloat(),
                    overview = movie.overview,
                    id = movie.id
                )
                findNavController().navigate(action, extras)
            }
        }
    }

    private fun downloadData() {
        when (FragmentPosition) {
            0 -> {
                myCategoryAdapter = CategoryMovieAdapter(0)
                recyclerViewMovie.layoutManager =LinearLayoutManager(activity)
                recyclerViewMovie.adapter = myCategoryAdapter
                movieViewModel.getMoviePopular()
                movieViewModel.getMovieRated()
                movieViewModel.getMovieTv()

                movieViewModel.moviePopular.observe(activity!!, Observer<List<MovieEntity>> {
                    myCategoryAdapter.addMoviesCategory(MovieCategoryEntity(categoryMovie = "Популярные фильмы",movies = it,gender = ""))
                })

                movieViewModel.movieRated.observe(activity!!, Observer<List<MovieEntity>> {
                    myCategoryAdapter.addMoviesCategory(MovieCategoryEntity(categoryMovie = "Лучшие фильмы",movies = it,gender = ""))
                })

                movieViewModel.seriesTv.observe(activity!!, Observer<List<MovieEntity>> {
                    myCategoryAdapter.addMoviesCategory(MovieCategoryEntity(categoryMovie = "Лучшие сериалы",movies = it,gender = ""))
                })
            }
            1 -> {
                movieViewModel.getAllCategoryMovie()
                myCategoryAdapter = CategoryMovieAdapter(1)
                recyclerViewMovie.layoutManager = GridLayoutManager(context,2)
                recyclerViewMovie.adapter = myCategoryAdapter
                movieViewModel.allCategoryMovie.observe(activity!!, Observer<List<MovieCategoryEntity>> {
                    val categoryMovie:MutableList<MovieCategoryEntity>  = ArrayList()
                    for ((index, value) in it.withIndex()) {
                        categoryMovie.add(MovieCategoryEntity(categoryMovie = genders[index],movies = value.movies, gender = MovieCategory.values()[index].name))
                    }
                    categoryMovie.forEach { movieCategory -> myCategoryAdapter.addMoviesCategory(movieCategory) }
                })
            }
            2 -> {
                myCategoryAdapter = CategoryMovieAdapter(0)
                recyclerViewMovie.layoutManager =LinearLayoutManager(activity)
                recyclerViewMovie.adapter = myCategoryAdapter

            }
            else -> throw Exception("Need Take 3")
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(counter: Int?): CardFragment {
            FragmentPosition = counter!!
            return CardFragment()
        }
    }
}
