package com.example.dmiryz.ryzhov.movieinfo.domain.converters

import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieDetailEntity

open class DetailMovieConverter() : MovieConverter() {

    var countGenders: Int = 0
    var countCountries: Int = 0
    var countCompanies: Int = 0

    fun fromDetailDateMovieToUI(detail: ResponseDetails): MovieDetailEntity {
        countGenders = detail.genres?.size ?: 0
        countCountries = detail.productionCountries?.size ?: 0
        countCompanies = detail.productionCompanies?.size ?: 0

        return MovieDetailEntity(
            budget = detail.budget,
            runtime = detail.runtime,
            genreOne = checkGensers(0)?.let { detail.genres?.get(it)?.name },
            genreTwo = checkGensers(1)?.let { detail.genres?.get(it)?.name },
            genreThree = checkGensers(2)?.let { detail.genres?.get(it)?.name },
            genreFoure = checkGensers(3)?.let { detail.genres?.get(it)?.name },
            revenue = detail.revenue,
            homepage = detail.homepage,

            firstCountryDevelop = checkCountries(0)?.let { detail.productionCountries?.get(it)?.name },
            secondCountryDevelop = checkCountries(1)?.let { detail.productionCountries?.get(it)?.name },
            thirdCountryDevelop = checkCountries(2)?.let { detail.productionCountries?.get(it)?.name },

            firstCompanyDevelop = checkCompanies(0)?.let { detail.productionCompanies?.get(it)?.name },
            secondCompanyDevelop = checkCompanies(1)?.let { detail.productionCompanies?.get(it)?.name },
            thirdCompanyDevelop = checkCompanies(2)?.let { detail.productionCompanies?.get(it)?.name },
            fourthCompanyDevelop = checkCompanies(3)?.let { detail.productionCompanies?.get(it)?.name }
        )
    }

    private fun checkGensers(number: Int): Int? {
        when (number) {
            0 -> if (number < countGenders) return 0
            1 -> if (number < countGenders) return 1
            2 -> if (number < countGenders) return 2
            3 -> if (number < countGenders) return 3
        }
        return null
    }

    private fun checkCountries(number: Int): Int? {
        when (number) {
            0 -> if (number < countCountries) return 0
            1 -> if (number < countCountries) return 1
            2 -> if (number < countCountries) return 2
        }
        return null
    }

    private fun checkCompanies(number: Int): Int? {
        when (number) {
            0 -> if (number < countCompanies) return 0
            1 -> if (number < countCompanies) return 1
            2 -> if (number < countCompanies) return 2
            3 -> if (number < countCompanies) return 3
        }
        return null
    }
}