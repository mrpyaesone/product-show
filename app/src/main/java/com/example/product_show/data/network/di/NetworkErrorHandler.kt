package com.example.product_show.data.network.di

import com.example.product_show.domain.exception.NetworkException
import com.example.product_show.data.network.response.ErrorResponse
import com.google.gson.GsonBuilder
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkErrorHandler @Inject constructor() {
    fun handleError(e: Throwable): Exception {
        if (e is HttpException) {
            return e.parseResponseError()
        }
        return Exception(e.localizedMessage)
    }

    private fun HttpException.parseResponseError(): Exception {
        val gson = GsonBuilder().create()
        println("parse HttpException: $this")
        return try {
            val errorBody = this.response()?.errorBody()?.source()?.buffer?.clone()?.readUtf8()
            println("parse HttpException: $errorBody")

            try {
                val error = gson.fromJson(errorBody, ErrorResponse::class.java)
                NetworkException(
                    code = "${error.code}",
                    errMessage = error.message ?: "Something went wrong"
                )
            } catch (e: Exception) {
                Exception(errorBody)
            }
        } catch (e: Exception) {
            Exception(e.localizedMessage)
        }
    }
}