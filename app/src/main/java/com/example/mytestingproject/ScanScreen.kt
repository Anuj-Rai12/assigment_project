package com.example.mytestingproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.mytestingproject.databinding.ScanActviityBinding
import com.example.mytestingproject.utils.LuminosityAnalyzer
import com.example.mytestingproject.utils.createLog
import com.example.mytestingproject.utils.msg
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ScanScreen : AppCompatActivity() {
    private lateinit var binding: ScanActviityBinding
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null

    private val recognizer by lazy {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScanActviityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cameraExecutor = Executors.newSingleThreadExecutor()
        startCamera()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(applicationContext)
        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(
                        cameraExecutor, LuminosityAnalyzer({ luma ->
                            createLog("LUMINOS_id", "$luma")
                            Log.d("TAG", "Average luminosity: $luma")
                        }, { imageInput, imgProxy ,img->
                           recognizer.process(imageInput).addOnSuccessListener {txt->
                               createLog("TEXT_RES","RES -> $txt")
                               msg("$txt")
                           }.addOnFailureListener {e->
                               createLog("TEXT_RES","error -> $e")
                           }.addOnCompleteListener {
                               img.close()
                               imgProxy.close()
                           }
                        })
                    )
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture, imageAnalyzer
                )

            } catch (exc: Exception) {
                createLog("Start_CAMERA", "error $exc")
            }

        }, ContextCompat.getMainExecutor(applicationContext))
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}