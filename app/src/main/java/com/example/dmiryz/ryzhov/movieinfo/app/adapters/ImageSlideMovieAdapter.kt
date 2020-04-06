package com.example.dmiryz.ryzhov.movieinfo.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.domain.models.ImageMovieEntity
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slide_item_movie_contaner.view.*

class ImageSlideMovieAdapter : RecyclerView.Adapter<ImageSlideMovieAdapter.SilderImageViewHolder>() {

    private var imageMovieList: MutableList<ImageMovieEntity> = ArrayList()

    fun addMovies(movies: List<ImageMovieEntity>) {
        this.imageMovieList.addAll(movies)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SilderImageViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_movie_contaner, parent, false)
        return SilderImageViewHolder(view)
    }

    override fun getItemCount(): Int  = imageMovieList.size ?: 0

    override fun onBindViewHolder(holder: SilderImageViewHolder, position: Int) {
        holder.bindImage(movieCategory = imageMovieList[position])
    }

    class SilderImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindImage(movieCategory: ImageMovieEntity) {
           itemView.imageMovieSlide.apply {
                Picasso.get()
                    .load(movieCategory.backDropPoster)
                    .noFade()
                    .into(this)
            }
        }
    }
}