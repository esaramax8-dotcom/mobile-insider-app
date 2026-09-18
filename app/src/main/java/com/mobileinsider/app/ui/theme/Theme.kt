package com.mobileinsider.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MobileInsiderDark =
    darkColorScheme(

        primary =
            Color(0xFF7C9CFF),

        secondary =
            Color(0xFF63D8C8),

        background =
            Color(0xFF090B12),

        surface =
            Color(0xFF111522),

        surfaceVariant =
            Color(0xFF1A2030)
    )

@Composable
fun MobileInsiderTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme =
            MobileInsiderDark,

        typography =
            Typography(),

        content =
            content
    )
}
