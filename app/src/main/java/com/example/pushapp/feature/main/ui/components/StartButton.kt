package com.example.pushapp.feature.main.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.PixelifySans
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
    label: String,
    helperLabel: String,
    timeLabel: String
) {
    val context = LocalContext.current

    Button(
        onClick = { Toast.makeText(context, "начать тренировку", Toast.LENGTH_SHORT).show() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 12.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.energy_icon),
                    contentDescription = "Начать тренировку"
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = PixelifySans
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 18.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = helperLabel,
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = PixelifySans
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.clock_icon),
                        contentDescription = "Время тренировки",
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = timeLabel,
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = PixelifySans
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34)
fun StartButtonPreview() {
    PushAppTheme(dynamicColor = false, darkTheme = false) {
        StartButton(label = "Начать", helperLabel = "ежедневная тренировка", timeLabel = "15 минут")
    }
}
