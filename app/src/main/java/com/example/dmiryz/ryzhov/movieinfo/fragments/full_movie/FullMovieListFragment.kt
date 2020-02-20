package com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.adapters.MovieAdapter
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateAppBarExpandedFunction
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.detail_movie_fragment.*
import kotlinx.android.synthetic.main.full_movie_list_fragment.*


class FullMovieListFragment : Fragment() {

    companion object {
        fun newInstance() = FullMovieListFragment()
    }

    val args: FullMovieListFragmentArgs by navArgs()

    private lateinit var viewModel: FullMovieListViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.full_movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FullMovieListViewModel::class.java)
        recyclerViewFullMovies.layoutManager = GridLayoutManager(context,3)
        stateAppBarExpandedFunction = true
        adapter = MovieAdapter()
        recyclerViewFullMovies.adapter = adapter

        val movieEntity = args.movieList.movies
        adapter.addMovies(movieEntity)


        adapter.movieSelectedListener = object : MovieAdapter.MovieSelectedListener {
            override fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title: TextView) {
                val extras = FragmentNavigatorExtras(
                    imageView to movie.posterPath,
                    title to  movie.title
                )
                val action = FullMovieListFragmentDirections.actionFullMovieListFragmentToDetailMovieFragment(
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
