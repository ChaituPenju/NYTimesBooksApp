package com.example.booksapp.repository

import com.example.booksapp.utils.Constants
import com.example.booksapp.models.Book
import com.example.booksapp.network.NYTService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val nytService: NYTService
) {
    suspend fun getBooks(): List<Book> {
        val response = nytService.getBooks(Constants.API_KEY)

        return response.results.books
    }
}