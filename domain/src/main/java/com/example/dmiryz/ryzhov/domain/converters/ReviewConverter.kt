package com.example.dmiryz.ryzhov.domain.converters

import com.example.dmiryz.ryzhov.data.remote.models.review.ReviewResult
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity

open class ReviewConverter : DetailMovieConverter() {

     fun fromReviewDateMovieToUI(review: ReviewResult): MovieReviewEntity {
        return MovieReviewEntity(
            author = review.author!!,
            content = review.content!!
        )
    }
}