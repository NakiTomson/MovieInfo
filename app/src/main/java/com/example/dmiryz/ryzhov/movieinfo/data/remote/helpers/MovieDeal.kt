package com.example.dmiryz.ryzhov.movieinfo.data.remote.helpers


fun main() {
    val movie:MovieRelation = TvDeal(MovieDeal())
    movie.movieWatch()

}

class MovieDeal :MovieRelation {

    override fun movieWatch() {
        println("Watch Movie")
    }
}

