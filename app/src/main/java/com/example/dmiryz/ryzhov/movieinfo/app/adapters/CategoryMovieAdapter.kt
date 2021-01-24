package com.example.dmiryz.ryzhov.movieinfo.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.MovieSectionFragmentDirections
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieCategoryEntity
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_card_with_movie.view.*

class CategoryMovieAdapter : RecyclerView.Adapter<CategoryMovieAdapter.CategoryMovieHolder>() {

    private var categroyMovieList: MutableList<MovieCategoryEntity> = ArrayList()
    lateinit var movieSelectedListener: RecommendedMovieAdapter.MovieSelectedListener


    interface MovieSelectedListener {
        fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title: TextView)
    }


    fun addMoviesCategory(moviesCategory: MovieCategoryEntity) {
        this.categroyMovieList.add(moviesCategory)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMovieHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.category_card_with_movie, parent, false)
        return CategoryMovieHolder(view)
    }

    override fun getItemCount(): Int = categroyMovieList.size

    override fun onBindViewHolder(holder: CategoryMovieHolder, position: Int) {
        holder.bindCategory(categroyMovieList[position], position)
    }

    inner class CategoryMovieHolder(itemCategory: View) : RecyclerView.ViewHolder(itemCategory) {
        fun bindCategory(movieCategory: MovieCategoryEntity, position: Int) {
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

}