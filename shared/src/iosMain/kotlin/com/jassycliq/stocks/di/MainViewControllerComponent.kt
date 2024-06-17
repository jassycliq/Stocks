package com.jassycliq.stocks.di

import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.jassycliq.stocks.core.di.ActivityScope
import com.jassycliq.stocks.ui.root.Content
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import platform.UIKit.UIViewController

@Component
@ActivityScope
abstract class MainViewControllerComponent(
    @Component val applicationComponent: ApplicationComponent
) : SharedActivityComponent {

    abstract val uiViewControllerFactory: () -> UIViewController

    @Provides
    @ActivityScope
    fun providesUiViewController(content: Content): () -> UIViewController = {
        ComposeUIViewController {
            content(Modifier)
        }
    }

    companion object
}
