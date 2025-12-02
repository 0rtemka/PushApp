package com.example.pushapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.Lora
import com.example.pushapp.ui.theme.PushAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = Lora,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        navigationIcon = {
            Surface(
                onClick = onBackClick,
                modifier = Modifier
                    .padding(8.dp)
                    .size(44.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 4.dp
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_icon),
                    contentDescription = "Назад",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true, name = "Light Mode")
fun TopAppBarLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        TopAppBar(
            title = "Заголовок",
            onBackClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, name = "Dark Mode")
fun TopAppBarDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        TopAppBar(
            title = "Заголовок",
            onBackClick = {}
        )
    }
}