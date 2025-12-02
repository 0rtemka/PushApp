package com.example.pushapp.feature.training.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import com.example.pushapp.feature.training.utils.secToTimerString
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun HeaderTimer(sec: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            painter = painterResource(R.drawable.clock_icon),
            contentDescription = "Таймер",
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = secToTimerString(sec),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderTimerLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        HeaderTimer(128)
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderTimerDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        HeaderTimer(128)
    }
}

