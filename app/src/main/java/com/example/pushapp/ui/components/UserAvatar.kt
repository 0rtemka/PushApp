package com.example.pushapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.pushapp.R
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun UserAvatar(praise: Boolean = false) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(200.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(if (praise) R.drawable.character_0_2 else R.drawable.character_0_1)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        translationY = size.height * 0.2f

                        scaleX = 1.4f
                        scaleY = 1.4f
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34, name = "Light Model")
fun UserAvatarLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        UserAvatar()
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34, name = "Dark Model")
fun UserAvatarDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        UserAvatar()
    }
}