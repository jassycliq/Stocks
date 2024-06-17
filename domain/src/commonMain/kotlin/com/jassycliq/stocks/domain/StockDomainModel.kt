package com.jassycliq.stocks.domain

data class StockDomainModel(
    val currency: String,
    val currentPrice: String,
    val sortableCurrentPrice: Int,
    val currentPriceRelativeTime: String,
    val name: String,
    val quantity: Int?,
    val ticker: String,
)
