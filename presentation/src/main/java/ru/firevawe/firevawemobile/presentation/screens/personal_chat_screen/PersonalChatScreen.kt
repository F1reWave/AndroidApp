package ru.firevawe.firevawemobile.presentation.screens.personal_chat_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.firevawe.firevawemobile.presentation.navigation.ScreenRoute
import ru.firevawe.firevawemobile.presentation.screens.base.ComposableScreen
import ru.firevawe.firevawemobile.presentation.utils.navigateeeee

internal class PersonalChatScreen(private val navController: NavController) : ComposableScreen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        var message by remember { mutableStateOf(TextFieldValue("")) }
        val messages = remember { mutableStateListOf<String>() }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Messenger") },
                    actions = {
                        IconButton(onClick = { /* TODO: Add action */ }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "More")
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .fillMaxHeight()
                        )
                        IconButton(onClick = {
                            if (message.text.isNotEmpty()) {
                                messages.add(message.text)
                                message = TextFieldValue("")
                            }
                        }) {
                            Icon(Icons.Default.Send, contentDescription = "Send")
                        }
                    }
                }
            }
        ) { innerPadding ->
            BackHandler { navController.navigateeeee(ScreenRoute.Main) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 8.dp), // Добавляем небольшой отступ снизу
                ) {
                    items(messages) { msg ->
                        Text(
                            text = msg,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }


}