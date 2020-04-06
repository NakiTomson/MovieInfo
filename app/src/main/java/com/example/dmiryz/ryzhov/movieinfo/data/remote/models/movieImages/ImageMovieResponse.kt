package com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movieImages

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ImageMovieResponse {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("backdrops")
    @Expose
    var backdrops: List<BackdropImageResult>? = null

    @SerializedName("posters")
    @Expose
    var posters: List<PosterImageResult?>? = null

}