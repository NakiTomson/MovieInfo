package com.example.dmiryz.ryzhov.data.remote.models.trailer

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class TrailerResult {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("site")
    @Expose
    var site: String? = null
    @SerializedName("size")
    @Expose
    var size = 0
    @SerializedName("type")
    @Expose
    var type: String? = null


}