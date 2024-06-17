package com.jassycliq.stocks.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.jassycliq.stocks.di.ActivityComponent
import com.jassycliq.stocks.di.create

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val activityComponent: ActivityComponent = ActivityComponent.create(
            activity = this,
            applicationComponent = (application as StocksApp).appComponent,
        )
        setContent {
            activityComponent.content(Modifier)
        }
    }
}
