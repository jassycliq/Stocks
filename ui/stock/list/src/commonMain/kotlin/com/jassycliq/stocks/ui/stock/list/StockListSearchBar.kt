package com.jassycliq.stocks.ui.stock.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.jassycliq.stocks.common.compose.ui.theme.StocksTheme
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnEmptyRequested
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnErrorRequested
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnQuery
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnRefreshList

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
internal fun StockListSearchBar(
    query: String,
    eventSink: (StockListUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        DockedSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            query = query,
            onQueryChange = { eventSink(OnQuery(it)) },
            onSearch = { focusManager.clearFocus() },
            enabled = StocksTheme.areInputsEnabled,
            active = false,
            onActiveChange = {},
            placeholder = { Text(text = "Search portfolio") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            },
            trailingIcon = {
                var expanded by remember { mutableStateOf(false) }
                var expandedDebug by remember { mutableStateOf(false) }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .size(40.dp)
                        .combinedClickable(
                            enabled = StocksTheme.areInputsEnabled,
                            onLongClick = { expandedDebug = true },
                            onClick = { expanded = true },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = androidx.compose.material.ripple.rememberRipple(
                                bounded = false,
                                radius = 40.dp / 2
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Sort,
                        contentDescription = null,
                    )

                    DebugDropdownMenu(
                        expanded = expandedDebug,
                        onDismissRequest = { expandedDebug = false },
                        eventSink = eventSink,
                    )

                    NormalDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        eventSink = eventSink,
                    )
                }
            },
            content = {},
        )
    }
}

@Composable
private fun NormalDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    eventSink: (StockListUiEvent) -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
    ) {
        DropdownMenuItem(
            leadingIcon = { Icon(imageVector = Icons.Default.SortByAlpha, contentDescription = null) },
            text = { Text("Alphabetical") },
            onClick = { eventSink(TODO()); onDismissRequest() }
        )
        DropdownMenuItem(
            leadingIcon = { Icon(imageVector = Icons.Default.CurrencyExchange, contentDescription = null) },
            text = { Text("Currency") },
            onClick = { eventSink(TODO()); onDismissRequest() }
        )
        DropdownMenuItem(
            leadingIcon = { Icon(imageVector = Icons.Default.Money, contentDescription = null) },
            text = { Text("Current price") },
            onClick = { eventSink(TODO()); onDismissRequest() }
        )
    }
}

@Composable
private fun DebugDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    eventSink: (StockListUiEvent) -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
    ) {
        DropdownMenuItem(
            leadingIcon = { Icon(imageVector = Icons.Default.Refresh, contentDescription = null) },
            text = { Text("Refresh state") },
            onClick = { eventSink(OnRefreshList); onDismissRequest() }
        )
        DropdownMenuItem(
            leadingIcon = { Icon(imageVector = Icons.Default.Error, contentDescription = null) },
            text = { Text("Error state") },
            onClick = { eventSink(OnErrorRequested); onDismissRequest() }
        )
        DropdownMenuItem(
            leadingIcon = { Icon(imageVector = Icons.Default.HourglassEmpty, contentDescription = null) },
            text = { Text("Empty state") },
            onClick = { eventSink(OnEmptyRequested); onDismissRequest() }
        )
    }
}
