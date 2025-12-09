package com.example.pushapp.feature.main.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.pushapp.R
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun UserInfo() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(R.drawable.character_0_1)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(256.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Light Model")
fun UserInfoLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        UserInfo()
    }
}

@Composable
@Preview(showBackground = true, name = "Dark Model")
fun UserInfoDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        UserInfo()
    }
}