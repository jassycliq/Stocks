package com.jassycliq.stocks.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockDto(
    @SerialName("currency")
    val currency: String,
    @SerialName("current_price_cents")
    val currentPriceCents: Int,
    @SerialName("current_price_timestamp")
    val currentPriceTimestamp: Long,
    @SerialName("name")
    val name: String,
    @SerialName("quantity")
    val quantity: Int?,
    @SerialName("ticker")
    val ticker: String,
)
