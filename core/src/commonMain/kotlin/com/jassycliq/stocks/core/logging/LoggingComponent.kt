package com.jassycliq.stocks.core.logging

import co.touchlab.kermit.Logger
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter
import com.jassycliq.stocks.core.di.ApplicationScope
import me.tatarka.inject.annotations.Provides

interface LoggingComponent {

    @Provides
    @ApplicationScope
    fun providesLogging(): Logger = Logger(config = loggerConfigInit(platformLogWriter()))
}
