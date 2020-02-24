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


        class GendersMovie{
            val Action:String = "28"
            val Adventure:String = "12"
            val Animation:String = "16"
            val Comedy:String = "35"
            val Crime:String = "80"
            val Drama:String = "18"
//            val Documentary:String = "99"
            val Family:String = "10751"
            val Fantasy:String = "14"
//            val History:String = "36"
            val Horror:String = "27"
//            val Music:String = "10402"
//            val Mystery:String = "9648"
            val Romance:String = "10749"
            val ScienceFiction:String = "878"
            val TVMovie:String = "10770"
            val Thriller:String = "53"
            val War:String = "10752"
            val Western:String = "37"
        }

        class GendersTv{
            val ActionAdventure:String = "10759"
            val Animation:String = "16"
            val Comedy:String = "35"
            val Crime:String = "80"
            val Documentary:String = "99"
            val Drama:String = "18"
            val Family:String = "10751"
            val Kids:String = "10762"
            val Mystery:String = "9648"
            val News:String = "10763"
            val Reality:String = "10764"
            val SciFiFantasy:String = "10765"
            val Soap:String = "10766"
            val Talk:String = "10767"
            val WarPolitics:String = "10768"
            val Western:String = "37"
        }

//        enum class MovieCategory(val title:String) {
//            `28`(title = "Action"), `12`(title = "Adventure"),
//            `16`(title = "Animation"), `35`(title = "Comedy"),
//            `80`(title = "Crime"), `18`(title = "Drama"),
//        }
    }
}