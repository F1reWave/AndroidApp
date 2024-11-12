//package ru.firevawe.firevawemobile.presentation.screens
//
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.launch
//import okhttp3.MediaType.Companion.toMediaType
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.RequestBody.Companion.toRequestBody
//import org.json.JSONObject
//import ru.firevawe.firevawemobile.presentation.screens.base.ComposableScreen
//
//class AuthScreen : ComposableScreen {
//    @Composable
//    override fun Content() {
//
//    }
//
//    @Composable
//    fun AuthScreen2() {
//        var username by remember { mutableStateOf("") }
//        var name by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//        var errorMessage by remember { mutableStateOf("") }
//
//        val coroutineScope = rememberCoroutineScope()
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Login")
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            BasicTextField(
//                value = username,
//                onValueChange = { username = it },
//                modifier = Modifier.fillMaxWidth().padding(8.dp),
//                singleLine = true
//            )
//            Text(text = "Username")
//
//            BasicTextField(
//                value = name,
//                onValueChange = { name = it },
//                modifier = Modifier.fillMaxWidth().padding(8.dp),
//                singleLine = true
//            )
//            Text(text = "Name")
//
//            BasicTextField(
//                value = password,
//                onValueChange = { password = it },
//                modifier = Modifier.fillMaxWidth().padding(8.dp),
//                singleLine = true
//            )
//            Text(text = "Password")
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    coroutineScope.launch {
//                        val result = loginUser(username, name, password)
//                        if (result) {
//                            // Navigate to another screen or show success
//                        } else {
//                            errorMessage = "Login failed"
//                        }
//                    }
//                }
//            ) {
//                Text(text = "Login")
//            }
//
//            if (errorMessage.isNotEmpty()) {
//                Text(text = errorMessage, color = Color.Red)
//            }
//        }
//    }
//
//    suspend fun loginUser(username: String, name: String, password: String): Boolean {
//        val client = OkHttpClient()
//
//        val json = JSONObject().apply {
//            put("username", username)
//            put("name", name)
//            put("password", password)
//        }
//
//        val requestBody = json.toString().toRequestBody("application/json".toMediaType())
//        val request = Request.Builder()
//            .url("http://192.168.0.104:8080/auth/login")
//            .post(requestBody)
//            .build()
//
//        val response = client.newCall(request).execute()
//        return response.isSuccessful
//    }
//
//
//}