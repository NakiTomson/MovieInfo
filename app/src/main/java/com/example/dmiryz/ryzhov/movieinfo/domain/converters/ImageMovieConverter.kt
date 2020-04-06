package com.example.dmiryz.ryzhov.movieinfo.domain.converters

import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movieImages.BackdropImageResult
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movieImages.ImageMovieResponse
import com.example.dmiryz.ryzhov.movieinfo.domain.models.ImageMovieEntity

class ImageMovieConverter : TraillerConverter() {

    fun fromImageDateResponseToImageEntity(model: BackdropImageResult): ImageMovieEntity {

        Configs.BASE_POSTER_URL + Configs.BIG_POSTER_SIZE
        return ImageMovieEntity(
            backDropPoster = Configs.BASE_POSTER_URL + Configs.BIG_POSTER_SIZE + model.filePath!!,
            posters = Configs.BASE_POSTER_URL + Configs.BIG_POSTER_SIZE + model.filePath!! // TODO поменфть если нужны будут posters
        )
    }
}