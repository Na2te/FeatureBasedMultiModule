package com.na2te.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.na2te.domain.usecase.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val loginUseCase: GetLoginUseCase
) : ViewModel() {
    private val _eventChannel = Channel<String>(Channel.BUFFERED)
    val eventFlow = _eventChannel.receiveAsFlow()

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            viewModelScope.launch(Dispatchers.IO) {
                _eventChannel.send("에러 발생: ${throwable.message}")
            }
        }

    fun login() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            loginUseCase()
        }
    }


}