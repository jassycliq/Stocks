package com.jassycliq.stocks.ui.stock.list

import com.jassycliq.stocks.core.di.ActivityScope
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

interface StockListComponent {
    @IntoSet
    @Provides
    @ActivityScope
    fun StockListPresenterFactory.bind(): Presenter.Factory = this

    @IntoSet
    @Provides
    @ActivityScope
    fun StockListUiFactory.bind(): Ui.Factory = this
}
