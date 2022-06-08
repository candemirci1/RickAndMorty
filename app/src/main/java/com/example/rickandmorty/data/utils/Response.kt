package com.example.rickandmorty.data.utils

sealed class Response<T>(
    val data: T? = null,
    val code: Int? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Response<T>(data)
    class Error<T>(message: String, code: Int? = null) : Response<T>(code = code, message = message)
    class Loading<T> : Response<T>()
}