package com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreResult {
    @SerializedName("id")
    @Expose
    var id = 0
    @SerializedName("name")
    @Expose
    var name: String? = null

}