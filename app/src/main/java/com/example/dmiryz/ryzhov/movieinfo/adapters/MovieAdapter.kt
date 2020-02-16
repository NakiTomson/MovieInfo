package com.example.dmiryz.ryzhov.movieinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main._item_movie.view.*

class MovieAdapter :  RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    lateinit var onEnd: onEndListener
    lateinit var movieSelectedListener: MovieSelectedListener


    private var movies: MutableList<MovieEntity> = ArrayList()

    interface MovieSelectedListener {
        fun onMovieSelected(movie: MovieEntity, imageView: ImageView)
    }

    interface onEndListener {
        fun onReachEnd()
    }

    fun setEndListener(onEndListener: onEndListener) {
        onEnd = onEndListener
    }


    fun addMovies(movies: List<MovieEntity>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout._item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (movies.size >= 20 && position > movies.size - 4) {
//            onEnd.onReachEnd()
        }
        holder.bindMovie(movie = movies[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.titlemovio)
        val year: TextView = itemView.findViewById(R.id.year)
        val rootMovie = itemView.root_movie

        fun bindMovie(movie: MovieEntity) {
            title.text = movie.title
            year.text = movie.year
            poster.apply {
                transitionName = movie.posterPath
                Picasso.get()
                    .load( movie.posterPath)
                    .placeholder(R.drawable.not_connect)
                    .noFade()
                    .into(this)
            }
            rootMovie.setOnClickListener {
                movieSelectedListener.onMovieSelected(movie, poster)
            }
        }

    }

}