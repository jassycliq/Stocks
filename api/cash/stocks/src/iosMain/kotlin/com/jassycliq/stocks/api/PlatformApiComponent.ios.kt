package com.jassycliq.stocks.api

import com.jassycliq.stocks.core.di.ApplicationScope
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.darwin.Darwin
import me.tatarka.inject.annotations.Provides

actual interface PlatformApiComponent {
    @Provides
    @ApplicationScope
    fun providesApiFactory(
        defaultApiConfig: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit,
    ): () -> HttpClient = {
        HttpClient(Darwin) {
            defaultApiConfig()
            engine {
                configureRequest {
                    setAllowsCellularAccess(true)
                }
            }
        }
    }
}
