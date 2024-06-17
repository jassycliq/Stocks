package com.jassycliq.stocks.di

import android.app.Application
import com.jassycliq.stocks.core.di.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class ApplicationComponent(
    @get:Provides val application: Application,
) : SharedApplicationComponent {
    companion object
}
