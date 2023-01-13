package com.example.mytestingproject

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.utils.msg
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        TedPermission.create()
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission] > [CAMERA]")
            .setPermissions(Manifest.permission.CAMERA)
            .setPermissionListener(
                object : PermissionListener {
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
}