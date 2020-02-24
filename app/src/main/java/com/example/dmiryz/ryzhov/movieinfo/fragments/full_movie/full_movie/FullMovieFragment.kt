package com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie.full_movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dmiryz.ryzhov.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.adapters.MovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.adapters.MovieAdapter.onEndListener
import com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie.SectionFullMovieListFragmentDirections
import com.example.dmiryz.ryzhov.movieinfo.utils.AppBarStateChangeListener
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.FullFragmentPosition
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.PAGE_ONE
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.PAGE_TWO
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.SORT_BY_POPULARITY
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.SORT_BY_RATED
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateAppBarExpandedFunction
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.full_movie_fragment.*


class FullMovieFragment() : Fragment() {

    private lateinit var viewModelFullMovie: FullMovieViewModel
    private lateinit var movieInfo: MovieCategoryEntity
    private lateinit var adapterMovie: MovieAdapter
    private lateinit var typeMovie: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.full_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelFullMovie = ViewModelProviders.of(this).get(FullMovieViewModel::class.java)
        initData()
        dowloadData()
        selectMovie()
    }

    private fun initData() {
        recyclerViewFullMovies.layoutManager = GridLayoutManager(context, 3)
        adapterMovie = MovieAdapter()
        recyclerViewFullMovies.adapter = adapterMovie
        movieInfo = arguments!!.getSerializable("ListMovieCategory") as MovieCategoryEntity
        typeMovie = movieInfo.movies[0].typeMovie
    }


    private fun dowloadData() {
        when (FullFragmentPosition) {
            0 -> {
                adapterMovie.addMovies(movieInfo.movies)
                viewModelFullMovie.moviePopular.observe(activity!!, Observer<List<MovieEntity>> {
                    adapterMovie.addMovies(it)
                })

                adapterMovie.onEnd = object : onEndListener {
                    override fun onReachEnd() {
                        PAGE_ONE++
                        viewModelFullMovie.getMoviePopular(PAGE_ONE,typeMovie,movieInfo.gender,SORT_BY_POPULARITY)
                    }
                }
            }
            1 -> {
                viewModelFullMovie.getMovieRated(PAGE_TWO,typeMovie,movieInfo.gender,SORT_BY_RATED)
                viewModelFullMovie.movieRated.observe(activity!!, Observer<List<MovieEntity>> {
                    adapterMovie.addMovies(it)
                })

                adapterMovie.onEnd = object : onEndListener {
                    override fun onReachEnd() {
                        PAGE_TWO++
                        viewModelFullMovie.getMovieRated(PAGE_TWO,typeMovie,movieInfo.gender,SORT_BY_RATED)
                    }
                }
            }
            else -> throw Exception("Need Take 3")
        }
    }



    private fun selectMovie() {
        adapterMovie.movieSelectedListener = object : MovieAdapter.MovieSelectedListener {
            override fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title: TextView) {
                val extras = FragmentNavigatorExtras(
                    imageView to movie.posterPath,
                    title to movie.title
                )
                val action =
                    SectionFullMovieListFragmentDirections.actionFullMovieListFragmentToDetailMovieFragment(
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

    companion object {
        @JvmStatic
        fun newInstance(counter: Int?): FullMovieFragment {
            FullFragmentPosition = counter!!
            return FullMovieFragment()
        }
    }

}
