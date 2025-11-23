package com.example.pushapp.feature.trainingPreparation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pushapp.R
import com.example.pushapp.feature.trainingPreparation.BatteryLevel
import com.example.pushapp.feature.trainingPreparation.TrainingPreparationViewModel
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun Intensity(viewModel: TrainingPreparationViewModel = viewModel()) {
    val level by viewModel.level.collectAsState()

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Интенсивность",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        viewModel.setLevel(
                            when (level) {
                                BatteryLevel.MEDIUM -> BatteryLevel.LOW
                                BatteryLevel.HIGH -> BatteryLevel.MEDIUM
                                else -> BatteryLevel.LOW
                            }
                        )
                    },
                    enabled = level !== BatteryLevel.LOW,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus_outlined_24),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Icon(
                    painter = painterResource(
                        when (level) {
                            BatteryLevel.LOW -> R.drawable.battery_low
                            BatteryLevel.MEDIUM -> R.drawable.battery_medium
                            BatteryLevel.HIGH -> R.drawable.battery_full
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        viewModel.setLevel(
                            when (level) {
                                BatteryLevel.LOW -> BatteryLevel.MEDIUM
                                BatteryLevel.MEDIUM -> BatteryLevel.HIGH
                                else -> BatteryLevel.HIGH
                            }
                        )
                    },
                    enabled = level !== BatteryLevel.HIGH,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_add_24),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun IntensityLightModePreview() {
    PushAppTheme(darkTheme = false) {
        Intensity()
    }
}

@Composable
@Preview(showBackground = true)
fun IntensityDarkModePreview() {
    PushAppTheme(darkTheme = true) {
        Intensity()
    }
}