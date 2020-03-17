package com.example.dmiryz.ryzhov.movieinfo.domain.converters

import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.review.ReviewResult
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieReviewEntity

open class ReviewConverter : DetailMovieConverter() {

     fun fromReviewDateMovieToUI(review: ReviewResult): MovieReviewEntity {
        return MovieReviewEntity(
            author = review.author!!,
            content = review.content!!
        )
    }
}