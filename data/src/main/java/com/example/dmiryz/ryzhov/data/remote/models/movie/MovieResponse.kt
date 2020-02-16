package com.example.dmiryz.ryzhov.data.remote.models.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MovieResponse {

    @SerializedName("page")
    @Expose
    var page = 0
    @SerializedName("total_results")
    @Expose
    var totalResults = 0
    @SerializedName("total_pages")
    @Expose
    var totalPages = 0
    @SerializedName("results")
    @Expose
    var results: List<MovieResult>? = null



}