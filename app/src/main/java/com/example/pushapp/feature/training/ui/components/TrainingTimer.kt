package com.example.pushapp.feature.training.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pushapp.R
import com.example.pushapp.feature.training.utils.secToTimerString
import com.example.pushapp.ui.theme.Lora
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun TrainingTimer(
    sec: Int,
    onIncreaseClick: (() -> Unit)? = null,
    onDecreaseClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            onDecreaseClick?.let { onClick ->
                IconButton(
                    onClick = onClick,
                    modifier = Modifier.size(44.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus),
                        contentDescription = "Убавить время",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text = secToTimerString(sec),
                style = TextStyle(
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Normal
                ),
                fontFamily = Lora,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            onIncreaseClick?.let { onClick ->
                IconButton(
                    onClick = onClick,
                    modifier = Modifier.size(44.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = "Добавить время",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TrainingTimerLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        TrainingTimer(sec = 228, onIncreaseClick = {}, onDecreaseClick = {})
    }
}

@Composable
@Preview(showBackground = true)
fun TrainingTimerDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        TrainingTimer(sec = 228, onIncreaseClick = {}, onDecreaseClick = {})
    }
}