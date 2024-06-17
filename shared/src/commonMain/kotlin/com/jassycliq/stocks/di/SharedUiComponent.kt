package com.jassycliq.stocks.di

import com.jassycliq.stocks.core.di.ActivityScope
import com.jassycliq.stocks.ui.stock.list.StockListComponent
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Provides

interface SharedUiComponent : StockListComponent {
    @Provides
    @ActivityScope
    fun providesCircuit(
        uiFactories: Set<Ui.Factory>,
        presenterFactories: Set<Presenter.Factory>,
    ): Circuit = Circuit.Builder()
        .addUiFactories(uiFactories)
        .addPresenterFactories(presenterFactories)
        .build()
}
