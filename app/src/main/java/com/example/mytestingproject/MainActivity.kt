package com.example.mytestingproject

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnCreate.setOnClickListener {
            val display=getSystemService(WINDOW_SERVICE) as WindowManager
            val disp=if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
                getDisplay()
            }else{
                display.defaultDisplay
            }
            val point=Point()
            disp?.getSize(point)
            val width=point.x
            val height=point.y
            var finalDisplay=if (width<height) width else height
            finalDisplay=(finalDisplay*3)/4
            Log.i("QR_INFO", "onCreate: $finalDisplay")

            val qr=QRGEncoder("123244",null,QRGContents.Type.TEXT,137)
            qr.colorBlack=Color.BLACK
            qr.colorWhite=Color.WHITE
            try {
                val bitmap=qr.getBitmap(0)
                binding.bitmapImg.setImageBitmap(bitmap)

            }catch (e:Exception){
                Log.i("QR_INFO", "onCreate: $e")
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}