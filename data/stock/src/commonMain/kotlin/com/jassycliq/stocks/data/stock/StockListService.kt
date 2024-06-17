package com.jassycliq.stocks.data.stock

import com.jassycliq.stocks.data.models.StockList

interface StockListService {
    suspend fun fetchSuccess(): StockList
    suspend fun fetchEmpty(): StockList
    suspend fun fetchError(): StockList
}
