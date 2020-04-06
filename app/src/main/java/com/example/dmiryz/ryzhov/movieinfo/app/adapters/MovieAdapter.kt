package com.example.dmiryz.ryzhov.movieinfo.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main._item_movie.view.*

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    constructor(movies: List<MovieEntity>) : this() {
        this.movies.addAll(movies)
    }

    private var movies: MutableList<MovieEntity> = ArrayList()
    lateinit var onEnd: onEndListener
    var onMovieDounload: Boolean = true
    var setFunctionDowloadMoreMovie: Boolean = true
    lateinit var movieSelectedListener: MovieSelectedListener

    interface MovieSelectedListener {
        fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title:TextView)
    }

    interface onEndListener {
        fun onReachEnd()
    }

    fun addMovies(movies: List<MovieEntity>) {
        this.movies.addAll(movies)
        onMovieDounload = true
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
        holder.bindMovie(movie = movies[position])
        if (movies.size >= 20 && position > movies.size - 2 && onMovieDounload) {
//            if (!setFunctionDowloadMoreMovie)return
            onEnd.onReachEnd()
            onMovieDounload = false
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.titlemovio)
        val year: TextView = itemView.findViewById(R.id.year)
        val rootMovie = itemView.root_movie


        fun bindMovie(movie: MovieEntity) {
            title.text = movie.title
            title.transitionName = movie.title
            year.text = movie.year
            poster.apply {
                Picasso.get()
                    .load(movie.posterPath)
                    .placeholder(R.drawable.not_connect)
                    .noFade()
                    .into(this)
            }
            rootMovie.setOnClickListener {
                poster.transitionName = movie.posterPath
                movieSelectedListener.onMovieSelected(movie, poster,title)
            }
        }

    }

}