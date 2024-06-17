package com.jassycliq.stocks.common.utils

import com.jassycliq.stocks.common.utils.PriceFormatter.formatDecimal
import com.jassycliq.stocks.common.utils.PriceFormatter.formatPrice
import kotlin.test.Test
import kotlin.test.assertEquals

class PriceFormatterTest {

    @Test
    fun `formatPrice formats price correctly`() {
        val result = 12345.formatPrice()
        assertEquals("123.45", result)
    }

    @Test
    fun `formatDecimal formats decimal correctly`() {
        val result = 12345.678.formatDecimal()
        assertEquals("12,345.67", result)
    }
}
