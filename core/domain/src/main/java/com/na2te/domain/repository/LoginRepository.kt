package com.na2te.domain.repository

interface LoginRepository {
    suspend fun getLogin(): Unit
}