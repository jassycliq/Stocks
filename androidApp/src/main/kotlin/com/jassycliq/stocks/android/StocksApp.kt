package com.jassycliq.stocks.android

import android.app.Application
import com.jassycliq.stocks.di.ApplicationComponent
import com.jassycliq.stocks.di.create

class StocksApp : Application() {
    val appComponent: ApplicationComponent = ApplicationComponent.create(this)
}
