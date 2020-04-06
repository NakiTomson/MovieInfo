package com.example.dmiryz.ryzhov.movieinfo.domain.converters

import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details.GenreResult
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.details.ResponseDetails
import com.example.dmiryz.ryzhov.movieinfo.domain.models.MovieDetailEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import java.lang.Exception

class DetailMovieConverterTest {

    var countGenders: Int = 0
    var countCountries: Int = 0
    var countCompanies: Int = 0


    lateinit var converter:DetailMovieConverter
    lateinit var detail:ResponseDetails
    lateinit var detailE: MovieDetailEntity


    @Before
    fun initResponseDetails(){
        converter = DetailMovieConverter()
        detail = ResponseDetails()
        val gender = GenreResult()
        gender.id = 1
        gender.name = "Hello"
        val numbers: MutableList<GenreResult> = mutableListOf(gender,gender)
        detail.genres = numbers
        countGenders = detail.genres?.size ?: 0
        converter.countGenders = this.countGenders
        detailE = MovieDetailEntity(budget = 0, runtime = 0, revenue = 0, homepage = "0", genreOne = converter.checkGensers(0)?.let { detail.genres?.get(it)?.name }, genreTwo = converter.checkGensers(1)?.let { detail.genres?.get(it)?.name }, genreThree = converter.checkGensers(2)?.let { detail.genres?.get(it)?.name }, genreFoure = converter.checkGensers(3)?.let { detail.genres?.get(it)?.name })
    }

    @Test
    fun checkGendersWhatCountSetInDetailsEntity() {
        assertFalse("It not right",detailE.genreOne == null)
        assertFalse("It not right",detailE.genreTwo == null)
        assertFalse("It not right",detailE.genreThree != null)
        assertFalse("It not right",detailE.genreFoure != null)
    }
}