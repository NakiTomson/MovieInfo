package com.example.dmiryz.ryzhov.movieinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.fragments.movie.MovieSectionFragmentDirections
import kotlinx.android.synthetic.main.item_category_movie.view.*

class CategoryMovieAdapter() : RecyclerView.Adapter<CategoryMovieAdapter.CategoryMovieHolder>() {

    constructor(movies: List<MovieCategoryEntity>) : this() {
        this.categroyMovieList.addAll(movies)
    }

    lateinit var movieSelectedListener: MovieSelectedListener
    private var categroyMovieList: MutableList<MovieCategoryEntity> = ArrayList()

    interface MovieSelectedListener {
        fun onMovieSelected(movie: MovieEntity, imageView: ImageView,title:TextView)
    }


    fun addMoviesCategory(moviesCategory: MovieCategoryEntity) {
        this.categroyMovieList.add(moviesCategory)
        notifyDataSetChanged()
    }

    inner class CategoryMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieCategory: TextView = itemView.movieCategory
        val nextFullListMovies: ImageButton = itemView.imageNextAllFilms
        val recycler: RecyclerView = itemView.recyclerViewCategory

        fun bind(gameCategroty: MovieCategoryEntity,position: Int) {
            movieCategory.text = gameCategroty.categoryMovie
            recycler.layoutManager = GridLayoutManager(itemView.context, 3)
            val movieAdapter = MovieAdapter(gameCategroty.movies.take(6))
            recycler.adapter = movieAdapter
            movieAdapter.movieSelectedListener = object : MovieAdapter.MovieSelectedListener {
                override fun onMovieSelected(movie: MovieEntity, imageView: ImageView,title:TextView) {
                    movieSelectedListener.onMovieSelected(movie, imageView,title)
                }
            }

            nextFullListMovies.setOnClickListener {
                val action = MovieSectionFragmentDirections.actionNavMovieToFullMovieListFragment(
                    movieList = categroyMovieList[position]
                )
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMovieHolder {
        return CategoryMovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_movie, parent, false))
    }

    override fun getItemCount(): Int = categroyMovieList.size

    override fun onBindViewHolder(holder: CategoryMovieHolder, position: Int) {
        holder.bind(categroyMovieList[position],position)
    }
}