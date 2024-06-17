package com.jassycliq.stocks.domain

import com.jassycliq.stocks.common.utils.PriceFormatter.formatPrice
import com.jassycliq.stocks.common.utils.TimeFormatter.getRelativeTime
import com.jassycliq.stocks.data.models.StockDto
import kotlinx.datetime.Instant

val transformStockDtoToDomainModel: (StockDto) -> StockDomainModel = { stockDto ->
    StockDomainModel(
        currency = stockDto.currency,
        currentPrice = stockDto.currentPriceCents.formatPrice(),
        sortableCurrentPrice = stockDto.currentPriceCents,
        currentPriceRelativeTime = Instant.fromEpochSeconds(stockDto.currentPriceTimestamp).getRelativeTime(),
        name = stockDto.name,
        quantity = stockDto.quantity,
        ticker = stockDto.ticker,
    )
}
