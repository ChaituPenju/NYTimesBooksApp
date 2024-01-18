package com.example.booksapp.network

import com.example.booksapp.models.NYTimesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTService {
    @GET("svc/books/v3/lists/current/hardcover-fiction.json")
    suspend fun getBooks(@Query("api-key") apiKey: String): NYTimesResponse
}