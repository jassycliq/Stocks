package com.jassycliq.stocks.common.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

actual val colorScheme: ColorScheme
    @Composable
    get() = when(isSystemInDarkTheme()) {
        true -> darkScheme
        false -> lightScheme
    }
