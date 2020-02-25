package com.example.dmiryz.ryzhov.data.remote.models.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseDetails {

    @SerializedName("adult")
    @Expose
    var isAdult = false
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: Any? = null
    @SerializedName("budget")
    @Expose
    var budget = 0
    @SerializedName("genres")
    @Expose
    var genres: List<GenreResult>? = null
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @SerializedName("id")
    @Expose
    var id = 0
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity = 0f
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
    @SerializedName("revenue")
    @Expose
    var revenue = 0
    @SerializedName("runtime")
    @Expose
    var runtime = 0
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("video")
    @Expose
    var isVideo = false
    @SerializedName("vote_average")
    @Expose
    var voteAverage = 0f
    @SerializedName("vote_count")
    @Expose
    var voteCount = 0

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null
    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>? = null

}