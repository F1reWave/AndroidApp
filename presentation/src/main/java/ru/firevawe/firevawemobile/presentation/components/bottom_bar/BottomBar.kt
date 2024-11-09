package ru.firevawe.firevawemobile.presentation.components.bottom_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.firevawe.firevawemobile.presentation.utils.suitableColor
import ru.firevawe.firevawemobile.resources.R


@Composable
internal fun BottomBar(
    enabled: Boolean = true,
    containerColor: Color,
    clickToMainScreen: () -> Unit = {},
    clickToChatsScreen: () -> Unit = {},
    clickToProfileScreen: () -> Unit = {},
    selectedMainScreen: Boolean = false,
    selectedChatsScreen: Boolean = false,
    selectedProfileScreen: Boolean = false,
) {
    val countOfItems = 3
    val colors = NavigationBarItemDefaults.colors(
        unselectedIconColor = containerColor.suitableColor().copy(alpha = 0.5f),
        indicatorColor = containerColor,
        disabledIconColor = containerColor.suitableColor(),
    )
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        containerColor = containerColor
    ) {
        NavigationBarItem(
            modifier = Modifier.weight(1f / countOfItems),
            selected = enabled && selectedMainScreen,
            onClick = clickToMainScreen,
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Main screen"
                )
            },
            colors = colors,
            enabled = !selectedMainScreen
        )
        NavigationBarItem(
            modifier = Modifier.weight(1f / countOfItems),
            selected = selectedChatsScreen,
            onClick = clickToChatsScreen,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = "Chats"
                )
            },
            enabled = enabled && !selectedChatsScreen,
            colors = colors
        )
        NavigationBarItem(
            modifier = Modifier.weight(1f / countOfItems),
            selected = enabled && selectedProfileScreen,
            onClick = clickToProfileScreen,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.person),
                    contentDescription = "Person screen"
                )
            },
            colors = colors,
            enabled = !selectedProfileScreen
        )
    }
}