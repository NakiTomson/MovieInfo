package com.example.dmiryz.ryzhov.domain.converters.factory

import com.example.dmiryz.ryzhov.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.data.remote.models.movie.MovieResult
import com.example.dmiryz.ryzhov.data.remote.models.review.ReviewResult
import com.example.dmiryz.ryzhov.data.remote.models.trailer.TrailerResult
import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.models.MovieEntity
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.models.MovieTraillerEntity

open class Converter {

    open fun fromDateMovieToUI(model: MovieResult): MovieEntity {
        return MovieEntity("bag","bag","10","10",0,0,0.0,"bag")
    }

    open fun fromDateTvToUI(model: MovieResult): MovieEntity {
        return MovieEntity("bag","bag","bag","bag",0,0,0.0,"bag")
    }

    open fun fromDetailDateMovieToUI(detail: ResponseDetails): MovieDetailEntity {
        return MovieDetailEntity("bag","bag","bag","bag",0,0)
    }

    open fun fromReviewDateMovieToUI(review: ReviewResult): MovieReviewEntity {
        return MovieReviewEntity("bag","bag")
    }


    open fun fromTraillerDateMovieToUI(trailler: TrailerResult): MovieTraillerEntity {
        return MovieTraillerEntity("bag","bag")
    }


}