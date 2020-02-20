package com.example.dmiryz.ryzhov.domain.converters

import com.example.dmiryz.ryzhov.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.domain.converters.factory.Converter
import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity

class DetailMovieConverter() : Converter() {

    var count: Int = 0

    override fun fromDetailDateMovieToUI(model: ResponseDetails): MovieDetailEntity {
        count = model.genres?.size ?: 0

        return MovieDetailEntity(
            budget = model.budget,
            runtime = model.runtime,
            genreOne = cheack(0)?.let { model.genres?.get(it)?.name },
            genreTwo = cheack(1)?.let { model.genres?.get(it)?.name },
            genreThree = cheack(2)?.let { model.genres?.get(it)?.name },
            genreFoure = cheack(3)?.let { model.genres?.get(it)?.name }
        )
    }

    fun cheack(number: Int): Int? {
        when (number) {
            0 -> if (number < count) return 0
            1 -> if (number < count) return 1
            2 -> if (number < count) return 2
            3 -> if (number < count) return 3
        }
        return null
    }
}