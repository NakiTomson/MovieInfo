package com.example.dmiryz.ryzhov.movieinfo.domain.converters

import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.trailer.TrailerResult
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieTraillerEntity

class TraillerConverter : ReviewConverter() {

     fun fromTraillerDateMovieToUI(trailler: TrailerResult): MovieTraillerEntity {
        return MovieTraillerEntity(
            nameVideo = trailler.name!!,
            keyTrailer = trailler.key!!
        )
    }

}