package com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.popular_full

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.app.adapters.MovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.app.adapters.MovieAdapter.onEndListener
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.SectionFullMovieListFragmentDirections
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.PAGE_ONE
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.SORT_BY_POPULARITY

import kotlinx.android.synthetic.main.full_movie_fragment.*


class PopularFullMovieFragment() : Fragment() {

    companion object {
        fun newInstance() = PopularFullMovieFragment()
    }

    private lateinit var popularViewModel: PopularFullMovieViewModel
    private lateinit var movieInfo: MovieCategoryEntity
    private lateinit var adapterMovie: MovieAdapter
    private lateinit var typeMovie: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.full_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        popularViewModel = ViewModelProvider(this).get(PopularFullMovieViewModel::class.java)
        initData()
        dowloadData()
        selectMovie()
    }

    private fun initData() {
        recyclerViewPopularFullMovies.layoutManager = GridLayoutManager(context, 3)
        adapterMovie = MovieAdapter()
        recyclerViewPopularFullMovies.adapter = adapterMovie
        movieInfo = arguments!!.getSerializable("ListMovieCategory") as MovieCategoryEntity
        typeMovie = movieInfo.movies[0].typeMovie
    }


    private fun dowloadData() {
        adapterMovie.addMovies(movieInfo.movies)
        popularViewModel.moviePopular.observe(activity!!, Observer<List<MovieEntity>> {
            adapterMovie.addMovies(it)
        })

        adapterMovie.onEnd = object : onEndListener {
            override fun onReachEnd() {
                PAGE_ONE++
                popularViewModel.getMoviePopular(PAGE_ONE, typeMovie, movieInfo.gender, SORT_BY_POPULARITY)
            }
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

}



