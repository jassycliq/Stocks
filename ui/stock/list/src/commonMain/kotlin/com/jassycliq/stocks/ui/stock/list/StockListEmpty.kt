package com.jassycliq.stocks.ui.stock.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun StockListEmpty(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            scale(scale = 1.5f, pivot = center) {
                drawEmptyCollectionCharacter()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("It's empty in here.", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif)
    }
}

private fun DrawScope.drawEmptyCollectionCharacter() {
    // Center the drawing within the Canvas
    val offsetX = size.width / 2 - 100f
    val offsetY = size.height / 2 - 50f

    translate(left = offsetX, top = offsetY) {
        // Draw the central rectangle
        val rectanglePath = Path().apply {
            moveTo(50f, 50f)
            lineTo(150f, 50f)
            lineTo(150f, 120f)
            lineTo(50f, 120f)
            close()
        }
        drawPath(rectanglePath, Color.Gray)

        // Draw the arms
        translate(left = 25f, top = 40f) {
            val armPath = Path().apply {
                moveTo(0f, 0f)
                lineTo(30f, 0f)
                lineTo(20f, 30f)
                lineTo(-10f, 30f)
                close()
            }
            drawPath(armPath, Color.Gray)
            translate(left = 100f) {
                drawPath(armPath, Color.Gray)
            }
        }

        drawCircle(Color.Black, radius = 5f, center = center.copy(x = 90f, y = 70f))
        drawCircle(Color.Black, radius = 5f, center = center.copy(x = 110f, y = 70f))
        drawLine(Color.Black, start = center.copy(x = 95f, y = 90f), end = center.copy(x = 105f, y = 90f), strokeWidth = 2f)
    }
}
