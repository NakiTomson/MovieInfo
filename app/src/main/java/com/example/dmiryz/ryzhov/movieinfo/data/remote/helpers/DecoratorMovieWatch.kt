package com.example.dmiryz.ryzhov.movieinfo.data.remote.helpers

open class DecoratorMovieWatch(val mov:MovieRelation) :MovieRelation {

    override fun movieWatch() {
        mov.movieWatch()
    }
}