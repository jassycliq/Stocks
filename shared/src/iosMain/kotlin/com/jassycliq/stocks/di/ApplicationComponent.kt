package com.jassycliq.stocks.di

import com.jassycliq.stocks.core.di.ApplicationScope
import me.tatarka.inject.annotations.Component

@Component
@ApplicationScope
abstract class ApplicationComponent : SharedApplicationComponent {
    companion object
}
