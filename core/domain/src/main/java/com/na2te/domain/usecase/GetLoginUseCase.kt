package com.na2te.domain.usecase

import com.na2te.domain.repository.LoginRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(): Unit = loginRepository.getLogin()
}