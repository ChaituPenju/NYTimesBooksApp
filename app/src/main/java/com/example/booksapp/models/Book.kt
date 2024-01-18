package com.example.booksapp.models

import com.google.gson.annotations.SerializedName

data class Book(
    val price: String,
    val title: String,
    val author: String,
    @SerializedName("book_image") val bookImage: String,
    var isChecked: Boolean = false
)
