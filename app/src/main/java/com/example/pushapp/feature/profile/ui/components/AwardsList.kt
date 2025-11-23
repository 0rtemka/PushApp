package com.example.pushapp.feature.profile.ui.components

import android.provider.SyncStateContract.Columns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.PixelifySans
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun AwardsSwiper() {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Достижения",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = PixelifySans,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(10) { item ->
                Award()
            }
        }
    }
}

@Composable
fun Award() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 8.dp).width(100.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.award1),
                contentDescription = "award",
                modifier = Modifier.size(90.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Название награды",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview(showBackground = false, apiLevel = 34)
fun AwardsSwiperLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        AwardsSwiper()
    }
}

@Composable
@Preview(showBackground = false, apiLevel = 34)
fun AwardsSwiperDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        AwardsSwiper()
    }
}