package ru.firevawe.firevawemobile.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.ChatNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.UserNetworkRepository

class MainActivity : ComponentActivity() {
    private val koin = getKoin()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val chatNetworkRepository = koin.get<ChatNetworkRepository>()
        val userNetworkRepository = koin.get<UserNetworkRepository>()
        val messageNetworkRepository = koin.get<MessageNetworkRepository>()
        lifecycleScope.launch(Dispatchers.IO) {
            chatNetworkRepository.createChat(ChatDomainModel(chatId = "1", isGroupChat = false))
            val x = chatNetworkRepository.getAllChats()
            Log.d("test", "chats = $x")
        }
        setContent {
//            MainTheme {
//                val navController = rememberNavController()
//                Column(
//                    modifier = Modifier.systemBarsPadding()
//                ) {
//                    NavHost(
//                        navController = navController, startDestination = ScreenRoute.Main.route
//                    ) {
//                        composable(ScreenRoute.Main.route) {
//                            MainScreen(
//                                navController = navController,
//                            ).Content()
//                        }
//                        composable(ScreenRoute.Chats.route) {
//                            ChatsScreen(
//                                navController = navController,
//                            ).Content()
//                        }
//                        composable(ScreenRoute.Profile.route) {
//                            ProfileScreen(
//                                navController = navController,
//                            ).Content()
//                        }
//                        composable(ScreenRoute.PersonalChat.route) {
//                            PersonalChatScreen(
//                                navController = navController,
//                            ).Content()
//                        }
//                    }
//                }
//            }
        }
    }
}