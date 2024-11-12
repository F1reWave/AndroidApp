package ru.firevawe.firevawemobile.domain.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//
//fun fetchData() {
//    val apiClient //= ApiClient()
//
//    // Получаем все чаты
//    CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val chats = apiClient.getAllChats()
//            println("Chats: $chats")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    // Получаем сообщения для чата
//    CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val messages = apiClient.getMessagesForChat("chatId123")
//            println("Messages: $messages")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    // Получаем пользователя по username
//    CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val user = apiClient.getUserByUsername("username123")
//            println("User: $user")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}
