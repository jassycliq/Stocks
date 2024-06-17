package com.jassycliq.stocks.ui.stock.list

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jassycliq.stocks.common.compose.ui.theme.StocksTheme
import com.jassycliq.stocks.common.compose.ui.theme.colorScheme
import com.jassycliq.stocks.domain.StockDomainModel

@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun LazyItemScope.StockListItem(stock: StockDomainModel) {
    Surface(
        onClick = {},
        enabled = StocksTheme.areInputsEnabled,
        shape = CardDefaults.shape,
        color = colorScheme.surface,
        contentColor = colorScheme.onSurface,
        tonalElevation = 4.dp,
        shadowElevation = 2.dp,
        modifier = Modifier
            .animateItemPlacement(
                animationSpec = tween(
                    durationMillis = 250,
                    easing = LinearOutSlowInEasing,
                )
            )
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Surface(
                    color = colorScheme.surfaceTint,
                    shape = RoundedCornerShape(50),
                ) {
                    Text(
                        text = stock.ticker,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorScheme.contentColorFor(colorScheme.surfaceTint),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .alignByBaseline(),
                    )
                }
                Text(
                    text = "${stock.currentPrice} ${stock.currency}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.alignByBaseline(),
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stock.name,
                    style = MaterialTheme.typography.titleMedium.copy(color = colorScheme.onSurface),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(weight = 1f).alignByBaseline(),
                )
                if (stock.quantity != null) {
                    Text(
                        text = "${stock.quantity} shares",
                        style = MaterialTheme.typography.bodySmall.copy(color = colorScheme.onSurfaceVariant),
                        modifier = Modifier.alignByBaseline(),
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.height(16.dp).fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    tint = colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stock.currentPriceRelativeTime,
                    style = MaterialTheme.typography.bodySmall.copy(color = colorScheme.onSurfaceVariant)
                )
            }
        }
    }
}
