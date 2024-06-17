package com.jassycliq.stocks.ui.root

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jassycliq.stocks.common.compose.ui.theme.StocksTheme
import com.jassycliq.stocks.common.ui.screens.StockListScreen
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.overlay.ContentWithOverlays
import com.slack.circuit.runtime.Navigator
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

typealias Content = @Composable (
    modifier: Modifier,
) -> Unit

@Inject
@Composable
fun Content(
    circuit: Circuit,
    @Assisted modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    StocksTheme(snackbarHostState = snackbarHostState) {
        CircuitCompositionLocals(circuit) {
            ContentWithOverlays(
                modifier = modifier.fillMaxHeight(),
            ) {
                val backstack: SaveableBackStack = rememberSaveableBackStack(StockListScreen)
                val navigator: Navigator = rememberCircuitNavigator(backstack) {}

                Scaffold(
                    contentWindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0),
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding(),
                ) { paddingValues ->
                    NavigableCircuitContent(
                        navigator = navigator,
                        backStack = backstack,
                        decoration = remember(navigator) {
                            GestureNavigationDecoration(onBackInvoked = navigator::pop)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .systemBarsPadding(),
                    )
                }
            }
        }
    }
}
