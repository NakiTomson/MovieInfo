package com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ProductionCountry {

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}