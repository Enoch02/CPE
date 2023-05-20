package com.cpe.data.models

data class News(
    val headline: String,
    val content: String,
    val coverImage: String = ""  // TODO: suitable type
)
