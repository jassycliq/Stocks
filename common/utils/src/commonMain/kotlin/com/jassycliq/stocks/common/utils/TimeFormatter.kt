package com.jassycliq.stocks.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

object TimeFormatter {
    fun Instant.getRelativeTime(): String {
        val now = Clock.System.now()
        val duration = now - this
        return when {
            duration.inWholeMinutes < 1 -> "just now"
            duration.inWholeMinutes == 1L -> "a minute ago"
            duration.inWholeMinutes < 60 -> "${duration.inWholeMinutes} minutes ago"
            duration.inWholeHours == 1L -> "an hour ago"
            duration.inWholeHours < 24 -> "${duration.inWholeHours} hours ago"
            duration.inWholeDays == 1L -> "a day ago"
            duration.inWholeDays < 7 -> "${duration.inWholeDays} days ago"
            duration.inWholeDays < 30 -> "${duration.inWholeDays / 7} weeks ago"
            duration.inWholeDays < 365 -> "${duration.inWholeDays / 30} months ago"
            duration.inWholeDays < 730 -> "a year ago"
            else -> "${duration.inWholeDays / 365} years ago"
        }
    }
}
