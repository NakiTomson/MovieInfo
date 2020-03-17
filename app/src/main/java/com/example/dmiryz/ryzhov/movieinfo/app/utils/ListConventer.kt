package com.example.dmiryz.ryzhov.movieinfo.app.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class ListConventer {


    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<Int>? {
        if (data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Int>?): String? {
        return gson.toJson(someObjects)
    }

}