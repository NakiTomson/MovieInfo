package com.example.dmiryz.ryzhov.movieinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.fragments.movie.MovieSectionFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_card_with_movie.view.*
import kotlinx.android.synthetic.main.item_category_movie.view.*


class CategoryMovieAdapter(var type: Int) :
    RecyclerView.Adapter<CategoryMovieAdapter.CategoryMovieHolder>() {

    constructor(type: Int, movies: List<MovieCategoryEntity>) : this(type) {
        this.categroyMovieList.addAll(movies)
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

        fun bind(movieCategory: MovieCategoryEntity, position: Int) {
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

        fun bind2(movieCategory: MovieCategoryEntity, position: Int) {
            val movieCategoryTitle: TextView = itemView.textCategoryMovie
            val movieOne: ImageView = itemView.imageMovieOne
            val movieTwo: ImageView = itemView.imageMovieTwo
            val movieThree: ImageView = itemView.imageMovieThree
            val roootInFullListMovie: CardView = itemView.rootCategoryCardWithMovie

            movieCategoryTitle.text = movieCategory.categoryMovie

            Picasso.get().
                load(movieCategory.movies[0].posterPath)
                .placeholder(R.drawable.not_connect)
                .noFade()
                .into(movieOne)

            Picasso.get().
                load(movieCategory.movies[1].posterPath)
                .placeholder(R.drawable.not_connect)
                .noFade()
                .into(movieTwo)

            Picasso.get().
                load(movieCategory.movies[2].posterPath)
                .placeholder(R.drawable.not_connect)
                .noFade()
                .into(movieThree)

            roootInFullListMovie.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    movieCategoryTitle to movieCategory.categoryMovie
                )
                val action = MovieSectionFragmentDirections.actionNavMovieToFullMovieListFragment(
                    movieList = categroyMovieList[position]
                )
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMovieHolder {
        val v: View = when (type) {
            0 -> LayoutInflater.from(parent.context).inflate(
                R.layout.item_category_movie,
                parent,
                false
            )
            1 -> LayoutInflater.from(parent.context).inflate(
                R.layout.category_card_with_movie,
                parent,
                false
            )
            2 -> LayoutInflater.from(parent.context).inflate(
                R.layout.category_card_with_movie,
                parent,
                false
            )
            else -> LayoutInflater.from(parent.context).inflate(
                R.layout.category_card_with_movie,
                parent,
                false
            )
        }
        return CategoryMovieHolder(v)
    }

    override fun getItemCount(): Int = categroyMovieList.size

    override fun onBindViewHolder(holder: CategoryMovieHolder, position: Int) {
        when (type) {
            0 -> holder.bind(categroyMovieList[position], position)
            1 -> holder.bind2(categroyMovieList[position], position)
            2 -> holder.bind2(categroyMovieList[position], position)
        }
    }
}