package com.example.dmiryz.ryzhov.movieinfo.data.remote.models.trailer

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class TrailerResponse {

    @SerializedName("id")
    @Expose
    var id = 0
    @SerializedName("results")
    @Expose
    var results: List<TrailerResult>? = null
}