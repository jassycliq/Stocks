package com.jassycliq.stocks.data.stock

import com.jassycliq.stocks.data.models.StockList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import me.tatarka.inject.annotations.Inject

@Inject
class StockListServiceImpl(
    private val cashStocksClient: () -> HttpClient,
) : StockListService {

    override suspend fun fetchSuccess(): StockList =
        cashStocksClient()
            .get(urlString = "portfolio.json") {
                contentType(Json)
            }.body()

    override suspend fun fetchEmpty(): StockList =
        cashStocksClient()
            .get(urlString = "portfolio_empty.json") {
                contentType(Json)
            }.body()

    override suspend fun fetchError(): StockList =
        cashStocksClient()
            .get(urlString = "portfolio_malformed.json") {
                contentType(Json)
            }.body()
}
