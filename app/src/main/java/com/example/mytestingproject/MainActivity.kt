package com.example.mytestingproject

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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



        binding.btnId2.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.test_file)

            val input = InputImage.fromBitmap(bitmap, 0)
            recognizer.process(input).addOnSuccessListener {result->
                //createLog("IMG_RES", "TEXT => ${GsonUtils.fromJsonToString(it)}")
                val resultText = result.text
                createLog("IMG_RES", "TEXT => $resultText")
                for (block in result.textBlocks) {
                    val blockText = block.text
                    val blockCornerPoints = block.cornerPoints
                    val blockFrame = block.boundingBox
                    createLog("IMG_RES", "blockText => $blockText")
                    createLog("IMG_RES", "blockCorner => $blockCornerPoints")
                    createLog("IMG_RES", "blockFrame => $blockFrame")
                    for (line in block.lines) {
                        val lineText = line.text
                        val lineCornerPoints = line.cornerPoints
                        val lineFrame = line.boundingBox
                        createLog("IMG_RES", "LineText => $lineText")
                        createLog("IMG_RES", "LineCornerPoints => $lineCornerPoints")
                        createLog("IMG_RES", "LineFrame => $lineFrame")
                        for (element in line.elements) {
                            val elementText = element.text
                            val elementCornerPoints = element.cornerPoints
                            val elementFrame = element.boundingBox
                            createLog("IMG_RES", "elementText => $elementText")
                            createLog("IMG_RES", "elementCornerPoints => $elementCornerPoints")
                            createLog("IMG_RES", "elementsFrame => $elementFrame")
                        }
                    }
                }
                /*it.textBlocks.forEach {res->
                    createLog("IMG_RES","TEXT RES -=> ${res.text}")
                    res.lines.forEach {line->
                        createLog("IMG_RES","TEXT RES -=> ${line.text}")
                        createLog("IMG_RES","CONFIDENCE RES -=> ${line.confidence}")
                        createLog("IMG_RES","ANGLE RES -=> ${line.angle}")
                        createLog("IMG_RES","ANGLE RES -=> ${line.elements}")
                        line.elements.forEach {txtElement->
                            createLog("IMG_RES","TEXT RES -=> ${txtElement.text}")
                            createLog("IMG_RES","ANGLE RES -=> ${txtElement.angle}")
                            createLog("IMG_RES","Symbols RES -=> ${txtElement.symbols}")
                            createLog("IMG_RES","Confidence RES -=> ${txtElement.confidence}")
                            txtElement.symbols.forEach { txtSy->
                            }
                        }
                    }
                }*/
            }.addOnFailureListener {
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
}