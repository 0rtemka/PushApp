package com.example.pushapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.PixelifySans
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun StartTrainingButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick() },
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
        }
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34)
fun StartButtonPreview() {
    PushAppTheme(dynamicColor = false, darkTheme = false) {
        StartTrainingButton(label = "Начать")
    }
}