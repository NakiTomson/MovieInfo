package com.example.dmiryz.ryzhov.movieinfo.data.remote.helpers

class TvDeal(mov: MovieRelation) : DecoratorMovieWatch(mov) {


    fun startWatchTv(){
        println("Wautch TV")
    }

    override fun movieWatch() {
        startWatchTv()
//        super.movieWatch()
    }
}