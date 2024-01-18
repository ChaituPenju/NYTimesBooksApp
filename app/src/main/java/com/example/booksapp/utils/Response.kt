package com.example.booksapp.utils

sealed class Response<T : Any>(
    val data: T? = null,
    val message: String? = null
) {
    class INITIAL<T : Any> : Response<T>()

    class LOADING<T: Any> : Response<T>()

    class SUCCESS<T: Any>(data: T) : Response<T>(data)

    class ERROR<T: Any>(data: T? = null, message: String) : Response<T>(data, message)
}