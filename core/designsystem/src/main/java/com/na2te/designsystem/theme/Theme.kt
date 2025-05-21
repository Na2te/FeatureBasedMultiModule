package com.na2te.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Composition locals
    CompositionLocalProvider (
        LocalColorScheme provides colorScheme,
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = MaterialTypography,
            content = content,
        )
    }
}

object ProjectTheme {
    val colorScheme: ProjectColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: ProjectTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography
}

/**
 * 이 어노테이션 아래의 함수가 true를 반환하면 최소한 api 이상이라는 걸 보장한다는 의미의 어노테이션
 * 이렇게 쓰면 이 함수를 if문으로 써서 내부에 관련 작업을 할 때 별도의 경고문이 생기지 않음
 */
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
