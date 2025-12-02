package com.example.pushapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.PixelifySans
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun UserLevel() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        LevelBadge("1", true)
        ExperienceProgressBar(
            currentExp = 10,
            expToNextLevel = 100,
            progressText = "+10 XP",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
        LevelBadge("2", false)
    }
}

@Composable
fun LevelBadge(title: String, fill: Boolean) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .wrapContentSize(Alignment.Center)
            .background(
                color = if (fill) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Icon(
            painter = painterResource(R.drawable.border),
            contentDescription = "Уровень",
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = if (fill) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
fun ExperienceProgressBar(
    currentExp: Int,
    expToNextLevel: Int,
    progressText: String,
    modifier: Modifier = Modifier,
) {
    val progress = if (expToNextLevel > 0) {
        currentExp.toFloat() / expToNextLevel.toFloat()
    } else 1f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        Text(
            text = progressText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = PixelifySans,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(22.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 4.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserLevelLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        UserLevel()
    }
}

@Composable
@Preview(showBackground = true)
fun UserLevelDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        UserLevel()
    }
}