package com.example.mytestingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val pdfUrl ="https://www.uptodd.com/resources/diet_chart/diet_chart_for_your_baby_30+_red.pdf"
       // "https://firebasestorage.googleapis.com/v0/b/steel-capsule-304313.appspot.com/o/qK9QAeXPihP10I3lZJwk9A2BYCk1%2FCA3%20CAP%20257.pdf?alt=media&token=d61d149a-4a8e-4ea1-9f4c-51e05b2870ae"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runBlocking {
            val obj = GetPdfResponse()
            obj.getPdfResponse(pdfUrl).collectLatest {
                it?.let { input ->
                    binding.pdfView.fromStream(input).defaultPage(0).enableSwipe(true)
                        .enableDoubletap(true).scrollHandle(object : DefaultScrollHandle(this@MainActivity) {})
                        .onPageChange { _, _ ->
                        }.spacing(2).nightMode(false).enableAnnotationRendering(true).load()
                } ?: this@MainActivity.run {
                    Toast.makeText(
                        this@MainActivity, "Error input stream is null", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        /*val assets=assets.open("Account_ca_content.pdf")
        binding.pdfView.fromStream(assets).defaultPage(0)
            .enableSwipe(true)
            .enableDoubletap(true)
            .scrollHandle(object : DefaultScrollHandle(this) {})
            .onPageChange { _, _ ->
            }
            .spacing(2)
            .nightMode(false)
            .enableAnnotationRendering(true)
            .load()*/
    }
}