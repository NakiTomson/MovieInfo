package com.example.dmiryz.ryzhov.movieinfo.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.MovieSectionFragmentDirections
import com.google.android.material.circularreveal.CircularRevealHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_card_with_movie.view.*
import kotlinx.android.synthetic.main.item_category_movie.view.*


class RecommendedMovieAdapter() : RecyclerView.Adapter<RecommendedMovieAdapter.CategoryMovieHolder>() {

    var Strategy = StrategyBind.bindRecommendedMovie
    constructor(strategy:StrategyBind) : this() {
        Strategy = strategy
    }

    lateinit var movieSelectedListener: MovieSelectedListener
    private var categroyMovieList: MutableList<MovieCategoryEntity> = ArrayList()

    interface MovieSelectedListener {
        fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title: TextView)
    }


    fun addMoviesCategory(moviesCategory: MovieCategoryEntity) {
        this.categroyMovieList.add(moviesCategory)
        notifyDataSetChanged()
    }


    inner class CategoryMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindRecommendedMovie(movieCategory: MovieCategoryEntity, position: Int) {
            val movieCategoryTitle: TextView = itemView.movieCategory
            val nextFullListMovies: ImageButton = itemView.imageNextAllFilms
            val recycler: RecyclerView = itemView.recyclerViewCategory

            movieCategoryTitle.text = movieCategory.categoryMovie
            recycler.layoutManager = GridLayoutManager(itemView.context, 3)
            val movieAdapter = MovieAdapter(movieCategory.movies.take(6))
            recycler.adapter = movieAdapter
            movieAdapter.movieSelectedListener = object : MovieAdapter.MovieSelectedListener {
                override fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title: TextView) {
                    movieSelectedListener.onMovieSelected(movie, imageView, title)
                }
            }

            nextFullListMovies.setOnClickListener {
                val action = MovieSectionFragmentDirections.actionNavMovieToFullMovieListFragment(
                    movieList = categroyMovieList[position]
                )
                val builder = NavOptions.Builder()
                val navOptions = builder
                    .setEnterAnim(R.animator.in_enter_anim)
                    .setExitAnim(R.animator.in_exit_anim)
                    .setPopEnterAnim(R.animator.out_enter_anim)
                    .setPopExitAnim(R.animator.out_exit_anim)
                    .build()
                it.findNavController().navigate(action,navOptions)
            }
        }


        fun bindSimiliarMovie(movieCategory: MovieCategoryEntity, position: Int) {
            val movieCategoryTitle: TextView = itemView.movieCategory
            val nextFullListMovies: ImageButton = itemView.imageNextAllFilms
            val recycler: RecyclerView = itemView.recyclerViewCategory

            movieCategoryTitle.text = movieCategory.categoryMovie
            recycler.layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
            val movieAdapter = MovieAdapter(movieCategory.movies.take(19))
            recycler.adapter = movieAdapter
            movieAdapter.movieSelectedListener = object : MovieAdapter.MovieSelectedListener {
                override fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title: TextView) {
                    movieSelectedListener.onMovieSelected(movie, imageView, title)
                }
            }

            nextFullListMovies.setOnClickListener {
                Toast.makeText(itemView.context,"NON",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMovieHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_category_movie, parent, false)
        return CategoryMovieHolder(v)
    }

    override fun getItemCount(): Int = categroyMovieList.size

    override fun onBindViewHolder(holder: CategoryMovieHolder, position: Int) {
        if (Strategy == StrategyBind.bindRecommendedMovie)
            holder.bindRecommendedMovie(categroyMovieList[position], position)
        else holder.bindSimiliarMovie(categroyMovieList[position], position)
    }

    companion object{
        enum class StrategyBind{
            bindRecommendedMovie,bindRetedMovie
        }
    }
}