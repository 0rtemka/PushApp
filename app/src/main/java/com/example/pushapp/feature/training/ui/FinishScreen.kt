package com.example.pushapp.feature.training.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pushapp.R
import com.example.pushapp.feature.training.ui.components.TrainingStats
import com.example.pushapp.ui.components.UserAvatar
import com.example.pushapp.ui.components.UserLevel
import com.example.pushapp.ui.theme.Lora
import com.example.pushapp.ui.theme.PixelifySans
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun FinishScreen(
    totalTime: Int,
    totalReps: Int,
    onReturnHome: () -> Unit
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.congratulations)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Тренировка завершена!",
                fontSize = 48.sp,
                lineHeight = 52.sp,
                fontFamily = PixelifySans,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )

            UserAvatar(praise = true)

            UserLevel()

            TrainingStats(
                time = ((totalTime + 59) / 60).toString(),
                pushUps = totalReps.toString(),
                kcal = "58"
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Share()
                HomeButton(onClick = onReturnHome)
            }
        }
    }
}

@Composable
fun Share() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "ПОДЕЛИТЬСЯ",
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = Lora
            )
            Icon(
                painter = painterResource(R.drawable.share),
                contentDescription = "Поделиться",
                modifier = Modifier.size(26.dp),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
fun HomeButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "ЗАВЕРШИТЬ",
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = Lora
            )
            Icon(
                painter = painterResource(R.drawable.arrow_icon),
                contentDescription = "Поделиться",
                modifier = Modifier
                    .size(26.dp)
                    .rotate(180f),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FinishScreenLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        FinishScreen(totalTime = 228, totalReps = 100, onReturnHome = {})
    }
}

@Composable
@Preview(showBackground = true)
fun FinishScreenDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        FinishScreen(totalTime = 228, totalReps = 100, onReturnHome = {})
    }
}