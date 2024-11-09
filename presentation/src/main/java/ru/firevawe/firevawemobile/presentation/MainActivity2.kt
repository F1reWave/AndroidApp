//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.graphics.BitmapFactory
//import android.graphics.ImageFormat
//import android.graphics.Rect
//import android.graphics.YuvImage
//import android.os.Bundle
//import android.util.Log
//import android.util.Size
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.camera.core.*
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.lifecycleScope
//import com.google.common.util.concurrent.ListenableFuture
//import io.ktor.client.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.plugins.HttpTimeout
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.plugins.websocket.*
//import io.ktor.serialization.kotlinx.json.*
//import io.ktor.websocket.*
//import kotlinx.coroutines.Dispatchers.IO
//import kotlinx.coroutines.isActive
//import kotlinx.coroutines.launch
//import kotlinx.serialization.json.Json
//import java.io.ByteArrayOutputStream
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//
//class MainActivity2 : ComponentActivity() {
//    private val messages = mutableStateListOf("Connecting...")
//    private val remoteCameraFrames = mutableStateListOf<ByteArray>()
//    private lateinit var client: HttpClient
//    private lateinit var socketSession: DefaultClientWebSocketSession
//    private lateinit var cameraExecutor: ExecutorService
//    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
//    private lateinit var previewView: PreviewView
//    private var isCameraActive by mutableStateOf(false)
//    private var imageSendErrorSent by mutableStateOf(false)
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
//        client = HttpClient(CIO) {
//            install(HttpTimeout) {
//                connectTimeoutMillis = 10000
//            }
//            install(WebSockets)
//            install(ContentNegotiation) {
//                json(Json { ignoreUnknownKeys = true })
//            }
//        }
//
//        cameraExecutor = Executors.newSingleThreadExecutor()
//
//        // Connect to WebSocket
//        lifecycleScope.launch(IO) {
//            socketSession = client.webSocketSession(
//                host = "5.tcp.eu.ngrok.io",
//                port = 19807,
//                path = "/ws"
//            )
//
//            // Check for camera permission
//            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                startCamera()
//            } else {
//                requestCameraPermission.launch(Manifest.permission.CAMERA)
//            }
//
//            messages.clear()
//            messages.add("Connected to WebSocket!")
//
//            while (client.isActive) {
//                // Listen for incoming messages
//                for (frame in socketSession.incoming) {
//                    when (frame) {
//                        is Frame.Binary -> {
//                            handleIncomingCameraFrame(frame.data)
//                        }
//
//                        is Frame.Text -> {
//                            messages.add("Received: ${frame.readText()}")
//                        }
//
//                        is Frame.Close -> {
//                            messages.add("WebSocket closed")
//                            socketSession.close()
//                        }
//
//                        is Frame.Ping -> {
//                            // Respond to the server's Ping frame with a Pong
//                            socketSession.send(Frame.Pong(frame.buffer))
//                            messages.add("Ping received, sent Pong in response")
//                        }
//
//                        is Frame.Pong -> {
//                            messages.add("Pong received from server, connection is alive")
//                        }
//                    }
//                }
//            }
//        }
//
//        setContent {
//            MainTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(modifier = Modifier.padding(innerPadding)) {
//                        previewView = PreviewView(this@MainActivity2)
//                        AndroidView(
//                            modifier = Modifier
//                                .fillMaxWidth(0.5f)
//                                .height(200.dp),
//                            factory = { previewView },
//                            update = {}
//                        )
//
//                        val camera = remoteCameraFrames.lastOrNull()
//                        camera?.let {
//                            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
//                            Image(
//                                bitmap = bitmap.asImageBitmap(),
//                                contentDescription = "Remote camera",
//                                modifier = Modifier
//                                    .size(100.dp)
//                                    .padding(4.dp)
//                            )
//                        }
//
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
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
//        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//        preview.setSurfaceProvider(previewView.surfaceProvider)
//
//        val imageAnalysis = ImageAnalysis.Builder()
//            .setTargetResolution(Size(1280, 720))
//            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//            .build()
//
//        imageAnalysis.setAnalyzer(cameraExecutor, ImageAnalysis.Analyzer { imageProxy ->
//            processImage(imageProxy)
//        })
//
//        try {
//            cameraProvider.unbindAll()
//            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
//        } catch (exc: Exception) {
//            messages.add("Error binding camera preview: ${exc.localizedMessage}")
//        }
//    }
//
//    private fun processImage(imageProxy: ImageProxy) {
//        try {
//            if (imageProxy.format != ImageFormat.YUV_420_888) {
//                if (!imageSendErrorSent) {
//                    messages.add("Unsupported image format: ${imageProxy.format}")
//                    imageSendErrorSent = true
//                }
//                return
//            }
//
//            val buffer = imageProxy.planes[0].buffer
//            val bytes = ByteArray(buffer.remaining())
//            buffer.get(bytes)
//
//            val yuvImage =
//                YuvImage(bytes, ImageFormat.NV21, imageProxy.width, imageProxy.height, null)
//            val stream = ByteArrayOutputStream()
//            yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 75, stream)
//            val jpegBytes = stream.toByteArray()
//
//            sendCameraFrame(jpegBytes)
//            stream.reset()
//        } catch (e: Exception) {
//            if (!imageSendErrorSent) {
//                Log.d("d", "Error processing image: ${e.localizedMessage}")
//                messages.add("Error processing image: ${e.localizedMessage}")
//                imageSendErrorSent = true
//            }
//        } finally {
//            imageProxy.close()
//        }
//    }
//
//    private fun handleIncomingCameraFrame(data: ByteArray) = remoteCameraFrames.add(data)
//
//    private fun sendCameraFrame(jpegBytes: ByteArray) {
//        lifecycleScope.launch(IO) {
//            try {
//                socketSession.send(Frame.Binary(fin = true, data = jpegBytes))
//            } catch (e: Exception) {
//                if (!imageSendErrorSent) {
//                    Log.d("d", "Error sending camera frame: ${e.localizedMessage}")
//                    messages.add("Error sending camera frame: ${e.localizedMessage}")
//                    imageSendErrorSent = true
//                }
//            }
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
//                Log.d("d", "Error starting camera: ${e.localizedMessage}")
//                messages.add("Error starting camera: ${e.localizedMessage}")
//            }
//        }, ContextCompat.getMainExecutor(this))
//    }
//
//    private fun stopCamera() {
//        cameraProviderFuture.get().unbindAll()
//        previewView.clearFocus()
//        messages.add("Camera stopped.")
//    }
//
//    private fun sendChatMessage(message: String) {
//        lifecycleScope.launch(IO) {
//            if (message.isNotBlank()) {
//                try {
//                    socketSession.send(Frame.Text(message))
//                    messages.add("You: $message")
//                } catch (e: Exception) {
//                    messages.add("Error sending message: ${e.localizedMessage}")
//                }
//            }
//        }
//    }
//}
