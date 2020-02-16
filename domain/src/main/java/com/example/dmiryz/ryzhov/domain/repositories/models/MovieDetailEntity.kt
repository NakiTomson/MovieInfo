package com.example.dmiryz.ryzhov.domain.repositories.models

data class MovieDetailEntity(
    val genreOne: String? = null,
    val genreTwo: String? = null,
    val genreThree: String? = null,
    val genreFoure: String? = null,
    val budget: Int,
    val runtime: Int
) {
}