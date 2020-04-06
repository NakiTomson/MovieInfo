package com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movieImages

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class BackdropImageResult {
    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Double? = null

    @SerializedName("file_path")
    @Expose
    var filePath: String? = null

    @SerializedName("height")
    @Expose
    private var height: Int? = null

    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null

}