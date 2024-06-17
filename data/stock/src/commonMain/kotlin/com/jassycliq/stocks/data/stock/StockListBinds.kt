package com.jassycliq.stocks.data.stock

import com.jassycliq.stocks.core.di.ApplicationScope
import me.tatarka.inject.annotations.Provides

interface StockListBinds {

    @Provides
    @ApplicationScope
    fun StockListServiceImpl.binds(): StockListService = this
}
