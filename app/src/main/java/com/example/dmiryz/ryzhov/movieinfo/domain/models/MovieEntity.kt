package com.example.dmiryz.ryzhov.movieinfo.domain.models

import java.io.Serializable

data class MovieEntity(
    val title: String,
    val posterPath: String,
    val year: String,
    val posterBackDropPath: String,
    var voteCount:Int,
    var id:Int,
    var voteAverage:Double,
    var overview:String,
    var typeMovie:String
) :Serializable{
}