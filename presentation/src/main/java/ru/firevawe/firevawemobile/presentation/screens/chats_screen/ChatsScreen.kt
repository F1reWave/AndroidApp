package ru.firevawe.firevawemobile.presentation.screens.chats_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.firevawe.firevawemobile.presentation.components.bottom_bar.BottomBar
import ru.firevawe.firevawemobile.presentation.navigation.ScreenRoute
import ru.firevawe.firevawemobile.presentation.screens.base.ComposableScreen
import ru.firevawe.firevawemobile.presentation.ui.theme.Theme
import ru.firevawe.firevawemobile.presentation.utils.navigateeeee

internal class ChatsScreen(private val navController: NavController) : ComposableScreen {
    @Composable
    override fun Content() {
        Scaffold(bottomBar = {
            BottomBar(
                containerColor = Theme.colors.mainColor,
                selectedChatsScreen = true,
                clickToMainScreen = { navController.navigateeeee(ScreenRoute.Profile) },
                clickToProfileScreen = { navController.navigateeeee(ScreenRoute.Profile) }
            )
        }) { innerPadding ->
            BackHandler { navController.navigateeeee(ScreenRoute.Main) }
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(text = "chats screen")
            }
        }
    }

}
