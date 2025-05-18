package com.na2te.data.datasource.remote

interface LoginRemoteDataSource {
    suspend fun getLogin(): Unit
}