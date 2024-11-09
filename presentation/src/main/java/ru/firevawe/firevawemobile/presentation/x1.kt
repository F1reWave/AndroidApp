// здесь работает камера, не крашится

//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.camera.core.*
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.lifecycleScope
//import com.google.common.util.concurrent.ListenableFuture
//import io.ktor.client.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.plugins.websocket.*
//import io.ktor.serialization.kotlinx.json.*
//import io.ktor.websocket.*
//import kotlinx.coroutines.launch
//import kotlinx.serialization.json.Json
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//
//class MainActivity : ComponentActivity() {
//    private val messages = mutableStateListOf("Connecting...")
//    private lateinit var client: DefaultClientWebSocketSession
//    private lateinit var cameraExecutor: ExecutorService
//    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
//    private lateinit var previewView: PreviewView
//    private var isCameraActive by mutableStateOf(false)
//
//    private val requestCameraPermission =
//        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//            if (isGranted) {
//                startCamera()
//            } else {
//                messages.add("Camera permission is required to use the camera.")
//            }
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        cameraExecutor = Executors.newSingleThreadExecutor()
//
//        // Check for camera permission
//        when {
//            checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
//                startCamera()
//            }
//            else -> {
//                requestCameraPermission.launch(Manifest.permission.CAMERA)
//            }
//        }
//
//        // Connect to WebSocket
//        lifecycleScope.launch {
//            client = HttpClient(CIO) {
//                install(ContentNegotiation) {
//                    json(Json { ignoreUnknownKeys = true })
//                }
//                install(WebSockets)
//            }.webSocketSession(host = "192.168.0.104", port = 8080, path = "/ws")
//
//            messages.clear()
//            messages.add("Connected to WebSocket!")
//
//            // Listen for incoming messages
//            for (frame in client.incoming) {
//                if (frame is Frame.Text) {
//                    messages.add(frame.readText())
//                }
//            }
//        }
//
//        setContent {
//            MainTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(modifier = Modifier.padding(innerPadding)) {
//                        // Create PreviewView here
//                        previewView = PreviewView(this@MainActivity)
//                        AndroidView(
//                            factory = { previewView },
//                            update = {}
//                        )
//
//                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                            Button(onClick = {
//                                if (isCameraActive) {
//                                    stopCamera()
//                                } else {
//                                    startCamera()
//                                }
//                                isCameraActive = !isCameraActive
//                            }) {
//                                Text(if (isCameraActive) "Stop Camera" else "Start Camera")
//                            }
//                        }
//
//                        ChatScreen(
//                            messages = messages,
//                            onSendMessage = { message ->
//                                sendChatMessage(message)
//                            },
//                            modifier = Modifier.weight(1f)
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
//        val preview = Preview.Builder().build()
//        val cameraSelector = CameraSelector.Builder()
//            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//            .build()
//
//        preview.setSurfaceProvider(previewView.surfaceProvider)
//
//        try {
//            cameraProvider.unbindAll()
//            cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
//        } catch (exc: Exception) {
//            messages.add("Error binding camera preview: ${exc.localizedMessage}")
//            exc.printStackTrace()
//        }
//    }
//
//    private fun startCamera() {
//        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
//        cameraProviderFuture.addListener({
//            try {
//                val cameraProvider = cameraProviderFuture.get()
//                bindPreview(cameraProvider)
//            } catch (e: Exception) {
//                messages.add("Error starting camera: ${e.localizedMessage}")
//                e.printStackTrace()
//            }
//        }, ContextCompat.getMainExecutor(this))
//    }
//
//    private fun stopCamera() {
//        // Убираем привязку камеры, если она была активна
//        val cameraProvider = cameraProviderFuture.get()
//        cameraProvider.unbindAll()
//        previewView.clearFocus()
//        messages.add("Camera stopped.")
//    }
//
//    private fun sendChatMessage(message: String) {
//        lifecycleScope.launch {
//            if (message.isNotBlank()) {
//                client.send(Frame.Text(message))
//                messages.add("You: $message")
//            }
//        }
//    }
//}
//
//@Composable
//fun ChatScreen(
//    messages: List<String>,
//    onSendMessage: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var messageText by remember { mutableStateOf("") }
//
//    Column(modifier = modifier.padding(16.dp)) {
//        // Display chat messages
//        Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
//            messages.forEach { message ->
//                Text(text = message, modifier = Modifier.padding(4.dp))
//            }
//        }
//
//        // Input field and send button
//        Row(modifier = Modifier.fillMaxWidth()) {
//            OutlinedTextField(
//                value = messageText,
//                onValueChange = { messageText = it },
//                modifier = Modifier.weight(1f).padding(4.dp),
//            )
//            Button(onClick = {
//                onSendMessage(messageText)
//                messageText = ""
//            }) {
//                Text("Send")
//            }
//        }
//    }
//}
