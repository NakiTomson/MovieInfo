package com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.category_movie_series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.dmiryz.ryzhov.movieinfo.R

class SeriesCategoryMovieFragment : Fragment() {

    companion object {
        fun newInstance() = SeriesCategoryMovieFragment()
    }

    private lateinit var viewModel: SeriesCategoryMovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.series_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeriesCategoryMovieViewModel::class.java)
    }

}
