package com.example.dmiryz.ryzhov.movieinfo.fragments.movie.card

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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmiryz.ryzhov.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity

import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.adapters.CategoryMovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.adapters.MovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.fragments.movie.MovieSectionFragmentDirections
import com.example.dmiryz.ryzhov.movieinfo.utils.AppBarStateChangeListener
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.countern
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.myPosition
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateAppBarExpandedFunction
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateOne
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateThree
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateTwo
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.card_fragment.*

class CardFragment : Fragment() {

    private lateinit var movieViewModel: CardViewModel
    lateinit var myCategoryAdapter: CategoryMovieAdapter
    lateinit var myMovieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.card_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java)
        myMovieAdapter = MovieAdapter()
        myCategoryAdapter = CategoryMovieAdapter()

        recyclerViewMovie.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = myCategoryAdapter
        }

        downloadData()
        changeConfigurationAppBar()

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
        when (countern) {
            0 -> {
                movieViewModel.getMoviePopular()
                movieViewModel.getMovieRated()
                movieViewModel.getMovieTv()

                movieViewModel.moviePopular.observe(activity!!, Observer<List<MovieEntity>> {
                    myCategoryAdapter.addMoviesCategory(MovieCategoryEntity(categoryMovie = "Популярные фильмы",movies = it))
                })

                movieViewModel.movieRated.observe(activity!!, Observer<List<MovieEntity>> {
                    myCategoryAdapter.addMoviesCategory(MovieCategoryEntity(categoryMovie = "Лучшие фильмы",movies = it))
                })

                movieViewModel.seriesTv.observe(activity!!, Observer<List<MovieEntity>> {
                    myCategoryAdapter.addMoviesCategory(MovieCategoryEntity(categoryMovie = "Лучшие сериалы",movies = it))
                })
            }
            1 -> {
                movieViewModel.getMovieRated()
                movieViewModel.movieRated.observe(activity!!, Observer<List<MovieEntity>> {

                })
            }
            2 -> {
                movieViewModel.getMovieTv()
                movieViewModel.seriesTv.observe(activity!!, Observer<List<MovieEntity>> {

                })
            }
            else -> throw Exception("Need Take 3")
        }
    }

    private fun changeConfigurationAppBar() {
        activity?.findViewById<AppBarLayout>(R.id.appBarLayout)?.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (stateAppBarExpandedFunction) return
                when (myPosition) {
                    0 ->  stateOne = getCurrentStateAppBar(sate = state.name)
                    1 -> stateTwo = getCurrentStateAppBar(sate = state.name)
                    2 -> stateThree = getCurrentStateAppBar(sate = state.name)
                    else -> throw Exception("it don't work")
                }
            }
        })

    }

    fun getCurrentStateAppBar(sate: String): Boolean {
        return when (sate) {
            "COLLAPSED" -> false
            else -> true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(counter: Int?): CardFragment {
            countern = counter!!
            return CardFragment()
        }
    }
}
