package com.na2te.start

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    viewModel: GreetingViewModel = hiltViewModel(),
    onShowSnackBar: suspend (message: String, action: String?) -> Boolean,
    onClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect {
            onShowSnackBar(it, "확인")
        }
    }

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick) {
            Text("다른 feature로 navigation")
        }
        Button(viewModel::login) {
            Text("API 테스트")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting(
        "Android",
        onShowSnackBar = {a, b ->
            delay(100)
            true
        },
    ) {}
}