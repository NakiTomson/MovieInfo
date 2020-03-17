package com.example.dmiryz.ryzhov.movieinfo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dmiryz.ryzhov.movieinfo.data.remote.models.movie.MovieResult

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): MutableList<MovieResult>

    @Query("SELECT * FROM movies WHERE id == :movieId")
    fun getMovieById(movieId: Int): MovieResult

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Insert
    fun insertMovie(movie: MovieResult?)

    @Delete
    fun deleteMovie(movie: MovieResult?)

}