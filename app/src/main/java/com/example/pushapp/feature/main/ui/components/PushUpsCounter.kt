package com.example.pushapp.feature.main.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.Lora
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun PushUpsCounter(
    icon: Painter,
    value: String,
    title: String,
    rounded: Dp,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(rounded),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = Lora,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.87f),
                textAlign = TextAlign.Center,
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = false, apiLevel = 34)
@Composable
fun PushUpsLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        PushUpsCounter(
            icon = painterResource(R.drawable.fire_icon),
            value = "100",
            title = "отжиманий",
            rounded = 20.dp
        )
    }
}

@Preview(name = "Dark Mode", showBackground = false, apiLevel = 34)
@Composable
fun PushUpsDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        PushUpsCounter(
            icon = painterResource(R.drawable.fire_icon),
            value = "332",
            title = "калории",
            rounded = 8.dp
        )
    }
}