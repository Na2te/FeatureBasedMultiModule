package com.na2te.start

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick){
            Text("다른 feature로 navigation")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android"){}
}