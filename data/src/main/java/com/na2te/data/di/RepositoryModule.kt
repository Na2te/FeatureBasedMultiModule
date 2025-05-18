package com.na2te.data.di

import com.na2te.data.repository.LoginRepositoryImpl
import com.na2te.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}