package com.jassycliq.stocks.common.utils

import com.jassycliq.stocks.common.utils.TimeFormatter.getRelativeTime
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeFormatterTest {

    @Test
    fun `getRelativeTime returns correct relative time`() {
        val now = Clock.System.now()
        val result = now.minus(3600, DateTimeUnit.SECOND).getRelativeTime()
        assertEquals("an hour ago", result)
    }
}
