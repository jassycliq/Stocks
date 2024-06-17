package com.jassycliq.stocks.common.ui.screens

import com.slack.circuit.runtime.screen.Screen as CircuitScreen

@CommonParcelize
data object StockListScreen : Screen(name = "StockListScreen()")

abstract class Screen(val name: String) : CircuitScreen, CommonParcelable {
    open val arguments: Map<String, *>? = null
}
