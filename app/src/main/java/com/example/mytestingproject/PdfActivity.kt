package com.example.mytestingproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.PdfActvityBinding
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

class PdfActivity : AppCompatActivity() {
    private val binding by lazy {
        PdfActvityBinding.inflate(layoutInflater)
    }
  //  val pdfUrl = "https://www.uptodd.com/resources/diet_chart/diet_chart_for_your_baby_30+_red.pdf"

    // "https://firebasestorage.googleapis.com/v0/b/steel-capsule-304313.appspot.com/o/qK9QAeXPihP10I3lZJwk9A2BYCk1%2FCA3%20CAP%20257.pdf?alt=media&token=d61d149a-4a8e-4ea1-9f4c-51e05b2870ae"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /*runBlocking {
            val obj = GetPdfResponse()
            obj.getPdfResponse(pdfUrl).collectLatest {
                it?.let { input ->
                    binding.pdfView.fromStream(input).defaultPage(0).enableSwipe(true)
                        .enableDoubletap(true)
                        .scrollHandle(object : DefaultScrollHandle(this@PdfActivity) {})
                        .onPageChange { _, _ ->
                        }.spacing(2).nightMode(false).enableAnnotationRendering(true).load()
                } ?: this@PdfActivity.run {
                    Toast.makeText(
                        this@PdfActivity, "Error input stream is null", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }*/


    }
}