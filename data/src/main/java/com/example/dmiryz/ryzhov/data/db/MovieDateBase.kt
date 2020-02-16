package com.example.dmiryz.ryzhov.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dmiryz.ryzhov.data.remote.models.movie.MovieResult

@Database(entities = [MovieResult::class], version = 1, exportSchema = false)
abstract class MovieDateBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        var INSTANCE: MovieDateBase? = null
        private val DB_NAME = "movies.db"

        fun getInstance(context: Context): MovieDateBase? {
            if (INSTANCE == null){
                synchronized(MovieDateBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MovieDateBase::class.java, DB_NAME).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}