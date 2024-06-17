package com.jassycliq.stocks.ui.stock.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jassycliq.stocks.common.compose.ui.overlays.loadingIndicatorOverlay
import com.jassycliq.stocks.common.compose.ui.theme.LocalStocksAreInputsEnabled
import com.jassycliq.stocks.common.compose.ui.theme.LocalStocksSnackbarHostState
import com.jassycliq.stocks.common.ui.screens.StockListScreen
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnRefreshList
import com.jassycliq.stocks.ui.stock.list.UserMessage.Error
import com.jassycliq.stocks.ui.stock.list.UserMessage.Loading
import com.slack.circuit.overlay.LocalOverlayHost
import com.slack.circuit.overlay.OverlayEffect
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.Inject

@Inject
class StockListUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is StockListScreen -> {
            ui { state: StockListUiState, modifier: Modifier ->
                StockList(state, modifier)
            }
        }
        else -> null
    }
}

@Composable
internal fun StockList(
    state: StockListUiState,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalStocksAreInputsEnabled provides (state.userMessage !is Loading)
    ) {
        // Need to extract the eventSink out to a local val, so that the Compose Compiler
        // treats it as stable. See: https://issuetracker.google.com/issues/256100927
        val eventSink: (StockListUiEvent) -> Unit = state.eventSink
        val overlayHost = LocalOverlayHost.current
        val snackbarHostState = LocalStocksSnackbarHostState.current

        OverlayEffect(state.userMessage) {
            when (state.userMessage) {
                is Error -> {
                    val result = snackbarHostState.showSnackbar(
                        message = state.userMessage.message,
                        actionLabel = "Retry",
                    )
                    if (result == ActionPerformed) eventSink(OnRefreshList)
                }
                is Loading -> overlayHost.show(loadingIndicatorOverlay)
                else -> Unit // No overlay needed
            }
        }

        Column(
            modifier = modifier
        ) {
            StockListSearchBar(
                query = state.query,
                eventSink = eventSink,
            )
            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (state.userMessage == null) {
                    items(
                        items = state.stockList,
                        key = { it.ticker },
                        contentType = { it },
                    ) {
                        StockListItem(it)
                    }
                    if (state.stockList.isEmpty()) {
                        item {
                            StockListEmpty()
                        }
                    }
                }
            }
        }
    }
}
