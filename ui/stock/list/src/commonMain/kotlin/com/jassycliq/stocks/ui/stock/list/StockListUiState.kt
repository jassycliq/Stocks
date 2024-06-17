package com.jassycliq.stocks.ui.stock.list

import com.jassycliq.stocks.domain.StockDomainModel
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class StockListUiState(
    val stockList: List<StockDomainModel>,
    val query: String = "",
    val userMessage: UserMessage? = null,
    val eventSink: (StockListUiEvent) -> Unit,
) : CircuitUiState

sealed interface UserMessage {
    data object Loading : UserMessage
    data class Error(val message: String = "There was an issue\nPlease try again") : UserMessage
}

sealed interface StockListUiEvent : CircuitUiEvent {
    data class OnStockTapped(val stock: StockDomainModel) : StockListUiEvent
    data class OnQuery(val query: String) : StockListUiEvent
    data object OnRefreshList : StockListUiEvent
    data object OnEmptyRequested : StockListUiEvent
    data object OnErrorRequested : StockListUiEvent
}
