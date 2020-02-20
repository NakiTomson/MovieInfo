package com.example.dmiryz.ryzhov.domain.models

import java.io.Serializable

data class MovieCategoryEntity(val categoryMovie:String,val movies:List<MovieEntity>):Serializable{}