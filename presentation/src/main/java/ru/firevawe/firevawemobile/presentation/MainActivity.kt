package ru.firevawe.firevawemobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.firevawe.firevawemobile.presentation.navigation.ScreenRoute
import ru.firevawe.firevawemobile.presentation.screens.chats_screen.ChatsScreen
import ru.firevawe.firevawemobile.presentation.screens.main_screen.MainScreen
import ru.firevawe.firevawemobile.presentation.screens.personal.ProfileScreen
import ru.firevawe.firevawemobile.presentation.screens.personal_chat_screen.PersonalChatScreen
import ru.firevawe.firevawemobile.presentation.ui.theme.MainTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainTheme {
                val navController = rememberNavController()
                Column(
                    modifier = Modifier.systemBarsPadding()
                ) {
                    NavHost(
                        navController = navController, startDestination = ScreenRoute.Main.route
                    ) {
                        composable(ScreenRoute.Main.route) {
                            MainScreen(
                                navController = navController,
                            ).Content()
                        }
                        composable(ScreenRoute.Chats.route) {
                            ChatsScreen(
                                navController = navController,
                            ).Content()
                        }
                        composable(ScreenRoute.Profile.route) {
                            ProfileScreen(
                                navController = navController,
                            ).Content()
                        }
                        composable(ScreenRoute.PersonalChat.route) {
                            PersonalChatScreen(
                                navController = navController,
                            ).Content()
                        }
                    }
                }
            }
        }
    }
}