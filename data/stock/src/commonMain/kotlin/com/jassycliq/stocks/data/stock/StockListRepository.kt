package com.jassycliq.stocks.data.stock

import com.jassycliq.stocks.core.di.ApplicationScope
import com.jassycliq.stocks.data.models.StockDto
import me.tatarka.inject.annotations.Inject

@Inject
@ApplicationScope
class StockListRepository(
    private val stockListService: StockListService,
) {

    suspend fun fetchSuccessStockList(): List<StockDto> =
        stockListService
            .fetchSuccess()
            .stocks

    suspend fun fetchErrorStockList(): List<StockDto> =
        stockListService
            .fetchError()
            .stocks

    suspend fun fetchEmptyStockList(): List<StockDto> =
        stockListService
            .fetchEmpty()
            .stocks
}
