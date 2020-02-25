package com.example.dmiryz.ryzhov.domain.models

data class MovieDetailEntity(
    val genreOne: String? = null,
    val genreTwo: String? = null,
    val genreThree: String? = null,
    val genreFoure: String? = null,
    val budget: Int,
    val runtime: Int,
    val revenue:Int,
    val homepage:String? = null,

    val firstCountryDevelop:String? = null,
    val secondCountryDevelop:String? = null,
    val thirdCountryDevelop:String? = null,

    val firstCompanyDevelop:String? = null,
    val secondCompanyDevelop:String? = null,
    val thirdCompanyDevelop:String? = null,
    val fourthCompanyDevelop:String? = null


) {
}