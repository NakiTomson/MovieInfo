package com.example.dmiryz.ryzhov.domain.converters

import com.example.dmiryz.ryzhov.data.remote.models.movie.MovieResult
import com.example.dmiryz.ryzhov.data.remote.models.review.ReviewResult
import com.example.dmiryz.ryzhov.data.utils.Configs
import com.example.dmiryz.ryzhov.domain.converters.factory.Converter
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieEntity
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieReviewEntity

class ReviewConverter : Converter(){

    override fun fromReviewDateMovieToUI(review: ReviewResult): MovieReviewEntity {
        return MovieReviewEntity(
            author = review.author!!,
            content = review.content!!
        )
    }
}