package com.example.pushapp.feature.training.ui

import PushUpsCounter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.pushapp.R
import com.example.pushapp.feature.training.ui.components.StageTitle
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun WorkScreen(
    reps: Int,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
    onDoneClick: () -> Unit
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        StageTitle(title = "Работа", description = "Классические отжимания")
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.classic_pushup)
                .build(),
            contentDescription = "Классические отжимания",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        PushUpsCounter(
            reps = reps,
            onIncreaseClick = onIncreaseClick,
            onDecreaseClick = onDecreaseClick
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DoneButton(modifier = Modifier.weight(1f), onClick = onDoneClick)
        }
    }
}

@Composable
fun DoneButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = "ГОТОВО",
            style = MaterialTheme.typography.titleLarge,
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WorkScreenLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        WorkScreen(reps = 99, onIncreaseClick = {}, onDecreaseClick = {}, onDoneClick = {})
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WorkScreenDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        WorkScreen(reps = 99, onIncreaseClick = {}, onDecreaseClick = {}, onDoneClick = {})
    }
}