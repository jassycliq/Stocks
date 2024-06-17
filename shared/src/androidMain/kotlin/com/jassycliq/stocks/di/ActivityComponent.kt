package com.jassycliq.stocks.di

import android.app.Activity
import com.jassycliq.stocks.core.di.ActivityScope
import com.jassycliq.stocks.ui.root.Content
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@ActivityScope
abstract class ActivityComponent(
    @get:Provides val activity: Activity,
    @Component val applicationComponent: ApplicationComponent,
) : SharedActivityComponent {
    abstract val content: Content

    companion object
}
