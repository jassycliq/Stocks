package com.jassycliq.stocks.di

import com.jassycliq.stocks.core.logging.LoggingComponent
import com.jassycliq.stocks.data.stock.StockListBinds

interface SharedApplicationComponent :
    LoggingComponent,
    NetworkComponent,
    StockListBinds
