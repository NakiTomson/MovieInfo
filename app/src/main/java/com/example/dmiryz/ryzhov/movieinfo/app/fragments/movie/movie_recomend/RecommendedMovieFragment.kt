package com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.movie_recomend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.app.adapters.RecommendedMovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.MovieSectionFragmentDirections

import kotlinx.android.synthetic.main.card_fragment.*

class RecommendedMovieFragment : Fragment() {

    companion object {
        fun newInstance() = RecommendedMovieFragment()
    }

    private lateinit var movieViewModel: RecommededMovieViewModel
    lateinit var myRecommededCategoryAdapter: RecommendedMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.card_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieViewModel = ViewModelProvider(this).get(RecommededMovieViewModel::class.java)
        downloadData()

        myRecommededCategoryAdapter.movieSelectedListener = object : RecommendedMovieAdapter.MovieSelectedListener {
            override fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title:TextView) {
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
        myRecommededCategoryAdapter = RecommendedMovieAdapter()
        recyclerViewMovie.layoutManager = LinearLayoutManager(activity)
        recyclerViewMovie.adapter = myRecommededCategoryAdapter
        movieViewModel.getMoviePopular()
        movieViewModel.getMovieRated()
        movieViewModel.getMovieTv()

        movieViewModel.moviePopular.observe(activity!!, Observer<List<MovieEntity>> {
            myRecommededCategoryAdapter.addMoviesCategory(
                MovieCategoryEntity(
                    categoryMovie = "Popular films",
                    movies = it,
                    gender = ""
                )
            )
        })

        movieViewModel.movieRated.observe(activity!!, Observer<List<MovieEntity>> {
            myRecommededCategoryAdapter.addMoviesCategory(
                MovieCategoryEntity(
                    categoryMovie = "Best films",
                    movies = it,
                    gender = ""
                )
            )
        })

        movieViewModel.seriesTv.observe(activity!!, Observer<List<MovieEntity>> {
            myRecommededCategoryAdapter.addMoviesCategory(
                MovieCategoryEntity(
                    categoryMovie = "Best films",
                    movies = it,
                    gender = ""
                )
            )
        })

    }
}
