package com.example.dmiryz.ryzhov.data.remote.models.review

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ReviewResult {

    @SerializedName("author")
    @Expose
    val author: String? = null
    @SerializedName("content")
    @Expose
    val content: String? = null
    @SerializedName("id")
    @Expose
    val id: String? = null
    @SerializedName("url")
    @Expose
    val url: String? = null
}