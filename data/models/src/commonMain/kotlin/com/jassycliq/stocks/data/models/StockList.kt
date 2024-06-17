package com.jassycliq.stocks.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockList(
    @SerialName("stocks")
    val stocks: List<StockDto>,
)
