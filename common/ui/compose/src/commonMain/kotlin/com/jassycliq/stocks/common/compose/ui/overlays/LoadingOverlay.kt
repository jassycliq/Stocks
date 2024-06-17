package com.jassycliq.stocks.common.compose.ui.overlays

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.slack.circuitx.overlays.BasicDialogOverlay

val loadingIndicatorOverlay = BasicDialogOverlay(
    model = Unit,
    onDismissRequest = {},
    properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
) { _, _ ->
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(.5F)
            .aspectRatio(1F)
            .padding(16.dp),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize(.65F)
                .padding(16.dp)
                .weight(weight = 1f, fill = false)
        )
    }
}
