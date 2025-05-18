package com.na2te.data.datasource.remote

import com.na2te.network.api.TestApi
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(private val testApi: TestApi): LoginRemoteDataSource {
    override suspend fun getLogin() = testApi.getLogin()
}