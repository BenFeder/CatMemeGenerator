package com.benfeder.catmemegenerator.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cat(
    val url: String
)