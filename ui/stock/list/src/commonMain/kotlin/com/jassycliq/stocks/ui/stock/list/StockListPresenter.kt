package com.jassycliq.stocks.ui.stock.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.jassycliq.stocks.common.ui.screens.StockListScreen
import com.jassycliq.stocks.domain.StockDomainModel
import com.jassycliq.stocks.domain.StockListUseCase
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnEmptyRequested
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnErrorRequested
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnQuery
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnRefreshList
import com.jassycliq.stocks.ui.stock.list.StockListUiEvent.OnStockTapped
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class StockListPresenterFactory(
    private val presenterFactory: (Navigator) -> StockListPresenter,
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext,
    ): Presenter<*>? = when (screen) {
        is StockListScreen -> presenterFactory(navigator)
        else -> null
    }
}

@Inject
class StockListPresenter(
    private val stockListUseCase: StockListUseCase,
    @Assisted private val navigator: Navigator,
) : Presenter<StockListUiState> {

    @Composable
    override fun present(): StockListUiState {
        val coroutineScope = rememberCoroutineScope()
        var userMessage by remember { mutableStateOf<UserMessage?>(null) }
        var stockList by remember { mutableStateOf<List<StockDomainModel>>(listOf()) }
        var query by remember { mutableStateOf("") }

        fun retrieveStockList(request: suspend () -> List<StockDomainModel>) = coroutineScope.launch {
            try {
                userMessage = UserMessage.Loading
                stockList = request()
                userMessage = null
            } catch (e: Exception) {
                userMessage = UserMessage.Error()
            }
        }

        LaunchedEffect(Unit) {
            retrieveStockList {
                stockListUseCase.fetchSuccessStockList()
            }
        }

        fun eventSink(event: StockListUiEvent) {
            when (event) {
                is OnQuery -> query = event.query
                is OnStockTapped -> navigator.goTo(TODO())
                is OnRefreshList -> retrieveStockList { stockListUseCase.fetchSuccessStockList() }
                is OnErrorRequested -> retrieveStockList { stockListUseCase.fetchErrorStockList() }
                is OnEmptyRequested -> retrieveStockList { stockListUseCase.fetchEmptyStockList() }
            }
        }

        return StockListUiState(
            stockList = stockList,
            query = query,
            userMessage = userMessage,
            eventSink = ::eventSink,
        )
    }
}
