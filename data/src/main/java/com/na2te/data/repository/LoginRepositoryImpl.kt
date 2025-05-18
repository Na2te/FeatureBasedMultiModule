package com.na2te.data.repository

import com.na2te.data.datasource.remote.LoginRemoteDataSource
import com.na2te.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginRemoteDataSource: LoginRemoteDataSource) :
    LoginRepository {
    override suspend fun getLogin() = loginRemoteDataSource.getLogin()
}