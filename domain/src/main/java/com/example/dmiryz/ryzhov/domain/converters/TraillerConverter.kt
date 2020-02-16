package com.example.dmiryz.ryzhov.domain.converters

import com.example.dmiryz.ryzhov.data.remote.models.trailer.TrailerResult
import com.example.dmiryz.ryzhov.domain.converters.factory.Converter
import com.example.dmiryz.ryzhov.domain.repositories.models.MovieTraillerEntity

class TraillerConverter : Converter() {

    override fun fromTraillerDateMovieToUI(trailler: TrailerResult): MovieTraillerEntity {
        return MovieTraillerEntity(
            nameVideo = trailler.name!!,
            keyTrailer = trailler.key!!
        )
    }

}