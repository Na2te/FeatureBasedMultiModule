package com.na2te.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.na2te.domain.usecase.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val loginUseCase: GetLoginUseCase
) : ViewModel() {
    fun login(){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase()
        }
    }


}