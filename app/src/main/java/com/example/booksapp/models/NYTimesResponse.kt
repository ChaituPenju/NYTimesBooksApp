package com.example.booksapp.models

import com.google.gson.annotations.SerializedName

// Importing necessary response(books), hence not included all the Json fields
data class NYTimesResponse(
    val results: Results
)

data class Results(
    val books: List<Book>
)