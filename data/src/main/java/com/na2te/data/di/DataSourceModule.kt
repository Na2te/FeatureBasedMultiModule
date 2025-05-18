package com.na2te.data.di

import com.na2te.data.datasource.remote.LoginRemoteDataSource
import com.na2te.data.datasource.remote.LoginRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindsLoginRemoteDataSource(loginRemoteDataSource: LoginRemoteDataSourceImpl): LoginRemoteDataSource
}