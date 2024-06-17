package com.jassycliq.stocks.api

import com.jassycliq.stocks.core.di.ApplicationScope
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.okhttp.OkHttp
import me.tatarka.inject.annotations.Provides
import okhttp3.OkHttpClient

actual interface PlatformApiComponent {
    @Provides
    @ApplicationScope
    fun providesOkHttpClientFactory(
//        interceptors: Lazy<Set<Interceptor>>,
    ): () -> OkHttpClient = {
        OkHttpClient
            .Builder()
//            .apply { interceptors.value.forEach(::addInterceptor) }
            .build()
    }

    @Provides
    @ApplicationScope
    fun providesApiFactory(
        okHttpClient: () -> OkHttpClient,
        defaultApiConfig: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit,
    ): () -> HttpClient = {
        HttpClient(OkHttp) {
            defaultApiConfig()
            engine {
                preconfigured = okHttpClient()
            }
        }
    }
}