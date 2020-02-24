package com.example.dmiryz.ryzhov.domain.models

import java.io.Serializable

data class MovieCategoryEntity(var categoryMovie:String, val movies:List<MovieEntity>,val gender:String):Serializable{}