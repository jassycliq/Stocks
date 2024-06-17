package com.jassycliq.stocks.domain

import com.jassycliq.stocks.core.di.ApplicationScope
import com.jassycliq.stocks.data.stock.StockListRepository
import me.tatarka.inject.annotations.Inject

@Inject
@ApplicationScope
class StockListUseCase(
    private val stockListRepository: StockListRepository,
) {
    private var stockList: List<StockDomainModel> = emptyList()

    suspend fun fetchSuccessStockList(): List<StockDomainModel> =
        stockListRepository
            .fetchSuccessStockList()
            .map(transformStockDtoToDomainModel)
            .also { stockList = it }

    suspend fun fetchErrorStockList(): List<StockDomainModel> =
        stockListRepository
            .fetchErrorStockList()
            .map(transformStockDtoToDomainModel)
            .also { stockList = it }

    suspend fun fetchEmptyStockList(): List<StockDomainModel> =
        stockListRepository
            .fetchEmptyStockList()
            .map(transformStockDtoToDomainModel)
            .also { stockList = it }
}
