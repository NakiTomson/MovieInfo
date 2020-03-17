package com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dmiryz.ryzhov.movieinfo.app.utils.ListConventer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
class MovieResult : Serializable {

    @PrimaryKey(autoGenerate = true)
    var uniqueId = 0

    @SerializedName("popularity")
    @Expose
    var popularity = 0.0
    @SerializedName("vote_count")
    @Expose
    var voteCount = 0
    @SerializedName("video")
    @Expose
    var isVideo = false
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("id")
    @Expose
    var id = 0
    @SerializedName("adult")
    @Expose
    var isAdult = false
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("genre_ids")
    @Expose
    @TypeConverters(ListConventer::class)
    var genreIds: List<Int>? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage = 0.0
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("name")
    @Expose
    var name:String? = null

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null




}