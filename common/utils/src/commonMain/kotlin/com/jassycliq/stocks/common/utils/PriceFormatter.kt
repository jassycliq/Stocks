package com.jassycliq.stocks.common.utils

object PriceFormatter {
    fun Int.formatPrice(): String {
        val price = this / 100.0
        return price.formatDecimal()
    }

    fun Double.formatDecimal(): String {
        val decimalPlaces = 2
        val parts = toString().split('.')
        val integerPart = parts[0].reversed().chunked(3).joinToString(",").reversed()
        val decimalPart = parts.getOrElse(1) { "0" }.padEnd(decimalPlaces, '0').substring(0, decimalPlaces)
        return "$integerPart.$decimalPart"
    }
}
