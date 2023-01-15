package com.example.mytestingproject

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.utils.createLog
import com.example.mytestingproject.utils.msg
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.gun0912.tedpermission.provider.TedPermissionProvider.context

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val recognizer by lazy {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra("url")?.let {
           // binding.imgView.setImageURI(it.toUri())
            val bitmap=MediaStore.Images.Media.getBitmap(contentResolver, it.toUri())
            binding.imgView.setImageBitmap(bitmap)
        } ?: binding.imgView.setImageResource(R.drawable.test_file)



        binding.btnId2.setOnClickListener {
            val bitmap=intent.getStringExtra("url")?.let {
                return@let MediaStore.Images.Media.getBitmap(contentResolver, it.toUri())
            } ?: BitmapFactory.decodeResource(context.resources, R.drawable.test_file)

            val input = InputImage.fromBitmap(bitmap, 0)
            recognizer.process(input).addOnSuccessListener { result ->
                val resultText = result.text
                dialog("Success", resultText)
                createLog("IMG_RES", "TEXT => $resultText")
            }.addOnFailureListener {
                dialog("Failed", it.localizedMessage ?: "unknown error")
                createLog("IMG_RES", "Error --> $it")
            }
        }





        TedPermission.create()
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission] > [CAMERA]")
            .setPermissions(Manifest.permission.CAMERA)
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    msg("Camera Permission Granted")
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    msg("CAMERA PERMISSION NOT Granted")
                }
            }).check()

        binding.btnId.setOnClickListener {
            val intent = Intent(this, ScanScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun dialog(title: String, msg: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(msg)
        dialog.setPositiveButton("ok") { _, _ ->

        }
        dialog.show()
    }

}