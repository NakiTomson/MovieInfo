package com.example.dmiryz.ryzhov.data.utils

class Configs {

    companion object{
        var BASE_URL = "https://api.themoviedb.org"
        var API_KEY = "0c1afb67c92f973df189d7aef68008bc"
        var SORT_BY_POPULARITY = "popularity.desc"
        var SORT_BY_RATED = "vote_average.desc"
        var LANGUAGE = "en-US"
        var VOTE_COUNT = 1000
        var TV_TYPE = "tv"
        var MOVIE_TYPE = "movie"

        val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
        val SMALL_POSTER_SIZE = "w185"
        val BIG_POSTER_SIZE = "w780"
    }
}