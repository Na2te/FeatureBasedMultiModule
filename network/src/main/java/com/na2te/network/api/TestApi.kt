package com.na2te.network.api

import retrofit2.http.GET

interface TestApi {
    @GET("login")
    suspend fun getLogin(): Unit
}