package com.jassycliq.stocks.domain

import com.jassycliq.stocks.data.models.StockDto
import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals

class StockListMapperTest {

    @Test
    fun `transformStockDtoToDomainModel correctly transforms StockDto to StockDomainModel`() {
        val now = Clock.System.now().epochSeconds
        val stockDto = StockDto(
            currency = "USD",
            currentPriceCents = 12345,
            currentPriceTimestamp = now - 3600, // 1 hour ago
            name = "Test Stock",
            quantity = 10,
            ticker = "TST"
        )

        val expected = StockDomainModel(
            currency = "USD",
            currentPrice = "123.45",
            sortableCurrentPrice = 12345,
            currentPriceRelativeTime = "an hour ago",
            name = "Test Stock",
            quantity = 10,
            ticker = "TST"
        )

        val result = transformStockDtoToDomainModel(stockDto)

        assertEquals(expected, result)
    }
}
