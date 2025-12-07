package com.example.pushapp.feature.profile.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.PixelifySans
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun SettingsMenu() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = PixelifySans,
            modifier = Modifier.padding(bottom = 14.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        SettingItem(painterResource(R.drawable.user_icon), "Персональные")
        SettingItem(painterResource(R.drawable.settings_icon), "Общие")
        SettingItem(painterResource(R.drawable.message_icon), "Уведомления")
    }
}

@Composable
fun SettingItem(icon: Painter, label: String) {
    val context = LocalContext.current

    Button(
        onClick = { Toast.makeText(context, "настройки", Toast.LENGTH_SHORT).show() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = icon,
                    contentDescription = label,
                    modifier = Modifier.size(22.dp)
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.short_arrow_icon),
                    contentDescription = "Open",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.37f)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34, name = "LightMode")
fun SettingsMenuLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        SettingsMenu()
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34, name = "DarkMode")
fun SettingsMenuDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        SettingsMenu()
    }
}