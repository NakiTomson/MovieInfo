package com.example.dmiryz.ryzhov.movieinfo.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.movieinfo.R


class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    private var reviews: List<MovieReviewEntity> = ArrayList()

    fun setReviews(movies: List<MovieReviewEntity>) {
        this.reviews = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ReviewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        val review: MovieReviewEntity = reviews[position]
        holder.author.text = review.author
        holder.message.text = review.content
    }


    override fun getItemCount(): Int {
        return reviews.size
    }

    inner class ReviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author: TextView = itemView.findViewById(R.id.textViewAuthority)
        var message: TextView = itemView.findViewById(R.id.textViewContent)
    }

}