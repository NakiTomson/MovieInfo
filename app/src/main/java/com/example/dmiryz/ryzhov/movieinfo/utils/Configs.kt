package com.example.dmiryz.ryzhov.movieinfo.utils

class Configs {


    companion object{

        var API_YOUTUBE_KEY = "AIzaSyCkso3lU92eEjOHnhn6alaowRkD6i3gGXU"

        //Sorting
        var SORT_BY_POPULARITY = "popularity.desc"
        var SORT_BY_RATED = "vote_average.desc"


        var FragmentPosition: Int = 0
        var FullFragmentPosition: Int = 0

        //состояния
        var stateOne:Boolean = true
        var stateTwo:Boolean = true
        var stateThree:Boolean = true
        var stateFoure:Boolean = true
        var stateFive:Boolean = true

        //будут ли меняться состояния выше
        var stateAppBarExpandedFunction:Boolean = false

        var myPositionOnViewPagersFragments:Int = 0


        //FullMovieFragment
        var PAGE_ONE = 1
        var PAGE_TWO = 1


    }
}