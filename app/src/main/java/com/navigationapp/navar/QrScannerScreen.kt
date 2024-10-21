package com.navigationapp.navar

import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat

@Composable
fun QrScannerScreen(onCodeScanned: (String) -> Unit) {
    var code by remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCamPermission = granted }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    if (hasCamPermission) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Camera Preview
            AndroidView(
                factory = { context ->
                    val previewView = PreviewView(context)
                    val preview = Preview.Builder().build()
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setTargetResolution(
                            Size(previewView.width, previewView.height)
                        )
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer { result ->
                            code = result
                            onCodeScanned(result)
                        }
                    )
                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )

            // Scanner Overlay
            ScannerOverlay(modifier = Modifier.fillMaxSize())

            // QR Code Text Display
            Text(
                text = code,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(32.dp)
            )
        }
    }
}

@Composable
fun ScannerOverlay(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val linePosition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cornerLength = 40.dp.toPx()
            val strokeWidth = 8.dp.toPx()
            val margin = 32.dp.toPx()
            val rectWidth = size.width - margin * 2
            val rectHeight = size.height - margin * 2
            val rectTopLeftX = (size.width - rectWidth) / 2
            val rectTopLeftY = (size.height - rectHeight) / 2

            // Draw the corners
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX, rectTopLeftY),
                end = Offset(rectTopLeftX + cornerLength, rectTopLeftY),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX, rectTopLeftY),
                end = Offset(rectTopLeftX, rectTopLeftY + cornerLength),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            // Top right corner
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX + rectWidth, rectTopLeftY),
                end = Offset(rectTopLeftX + rectWidth - cornerLength, rectTopLeftY),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX + rectWidth, rectTopLeftY),
                end = Offset(rectTopLeftX + rectWidth, rectTopLeftY + cornerLength),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            // Bottom left corner
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX, rectTopLeftY + rectHeight),
                end = Offset(rectTopLeftX + cornerLength, rectTopLeftY + rectHeight),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX, rectTopLeftY + rectHeight),
                end = Offset(rectTopLeftX, rectTopLeftY + rectHeight - cornerLength),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            // Bottom right corner
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX + rectWidth, rectTopLeftY + rectHeight),
                end = Offset(rectTopLeftX + rectWidth - cornerLength, rectTopLeftY + rectHeight),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(rectTopLeftX + rectWidth, rectTopLeftY + rectHeight),
                end = Offset(rectTopLeftX + rectWidth, rectTopLeftY + rectHeight - cornerLength),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            // Scanning line
            val scanLineY = rectTopLeftY + rectHeight * linePosition
            drawLine(
                color = Color.Green,
                start = Offset(rectTopLeftX, scanLineY),
                end = Offset(rectTopLeftX + rectWidth, scanLineY),
                strokeWidth = 4.dp.toPx()
            )
        }
    }
}


