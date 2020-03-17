package com.example.dmiryz.ryzhov.movieinfo.data.remote.models.review

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ReviewResponse {
    @SerializedName("id")
    @Expose
    val id = 0
    @SerializedName("page")
    @Expose
    val page = 0
    @SerializedName("results")
    @Expose
    val results: List<ReviewResult>? = null
    @SerializedName("total_pages")
    @Expose
    val totalPages = 0
    @SerializedName("total_results")
    @Expose
    val totalResults = 0
}