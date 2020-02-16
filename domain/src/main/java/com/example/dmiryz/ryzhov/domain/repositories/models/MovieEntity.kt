package com.example.dmiryz.ryzhov.domain.repositories.models

data class MovieEntity(
    val title: String,
    val posterPath: String,
    val year: String,
    val posterBackDropPath: String,
    var voteCount:Int,
    var id:Int,
    var voteAverage:Double,
    var overview:String
) {
}