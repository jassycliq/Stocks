package com.jassycliq.stocks.common.compose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

object StocksTheme {
    @Composable
    operator fun invoke(
        snackbarHostState: SnackbarHostState,
        areInputsEnabled: Boolean = true,
        content: @Composable () -> Unit,
    ) {
        CompositionLocalProvider(
            LocalStocksSnackbarHostState provides snackbarHostState,
            LocalStocksAreInputsEnabled provides areInputsEnabled,
        ) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = StocksTypography,
                content = content,
            )
        }
    }

    val areInputsEnabled: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalStocksAreInputsEnabled.current

    val snackbarHostState: SnackbarHostState
        @Composable
        @ReadOnlyComposable
        get() = LocalStocksSnackbarHostState.current
}

val LocalStocksAreInputsEnabled: ProvidableCompositionLocal<Boolean> = compositionLocalOf { true }

val LocalStocksSnackbarHostState: ProvidableCompositionLocal<SnackbarHostState> = compositionLocalOf {
    error("SnackbarHostState not provided")
}
