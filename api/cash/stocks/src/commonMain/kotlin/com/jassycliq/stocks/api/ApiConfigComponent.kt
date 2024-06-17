package com.jassycliq.stocks.api

import com.jassycliq.stocks.core.di.ApplicationScope
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Provides

import co.touchlab.kermit.Logger as KermitLogger
import io.ktor.client.plugins.logging.Logger as KtorLogger

interface ApiConfigComponent {
    @Provides
    @ApplicationScope
    fun providesApiConfig(
        kermitLogger: KermitLogger
    ): HttpClientConfig<out HttpClientEngineConfig>.() -> Unit = {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json)
        }
        install(Logging) {
            level = LogLevel.BODY
            logger = object : KtorLogger {
                override fun log(message: String) {
                    kermitLogger.i(message, tag = "NETWORK")
                }
            }
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "storage.googleapis.com"
                path("cash-homework/cash-stocks-api/")
            }
        }
    }
}
