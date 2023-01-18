package com.example.mytestingproject.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Log
import com.example.mytestingproject.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.Writer
import com.google.zxing.qrcode.QRCodeWriter
import com.paytm.printgenerator.Alignment
import com.paytm.printgenerator.page.ImageElement
import com.paytm.printgenerator.page.Page
import com.paytm.printgenerator.page.TextElement
import com.paytm.printgenerator.utils.PrinterUtils

class PrintFile(context: Context) {
    private val size = 150
    private val info = "hello testing"
    private val line = "-------------------------------------------------------------"
    private val icon: Bitmap = PrinterUtils.bitmapToBlack(
        BitmapFactory.decodeResource(
            context.resources,
            R.raw.g20_india_logo_s
        ), 110
    )

    fun checkIn(): Page? {
        try {
            val page = Page()
            val qrBitmap = getBitmap()
            page.addLine().addElement(
                TextElement(
                    text = "Check-In Ticket : 789785456",
                    alignment = Alignment.CENTER
                )
            )
            page.addLine().addElement(
                TextElement(
                    text = line,
                    alignment = Alignment.CENTER
                )
            )
            page.addLine()
                .addElement(TextElement(text = "Harigo(Test)", alignment = Alignment.CENTER))
            page.addLine().addElement(
                TextElement(
                    text = "Auth by : Municipal Corporation of Delhi",
                    alignment = Alignment.LEFT
                )
            )
            page.addLine().addElement(
                TextElement(
                    text = "Check In Date & Time : 14/2/23 , 05:30pm",
                    alignment = Alignment.LEFT
                )
            )
            page.addLine().addElement(
                TextElement(
                    text = "Vehicle : Bike || Vehicle No : 1111",
                    alignment = Alignment.LEFT
                )
            )
            page.addLine().addElement(
                TextElement(
                    text = line,
                    alignment = Alignment.CENTER
                )
            )
            page.addLine().addElement(TextElement(text = "Charges:", alignment = Alignment.LEFT))
            page.addLine()
                .addElement(TextElement(text = "Per Hour = Rs 10.00", alignment = Alignment.LEFT))
            page.addLine()
                .addElement(TextElement(text = "24 Hours : Rs 40:00", alignment = Alignment.LEFT))
            page.addLine().addElement(
                TextElement(
                    text = line,
                    alignment = Alignment.CENTER
                )
            )
            page.addLine().addElement(ImageElement(image = qrBitmap, alignment = Alignment.CENTER))
            page.addLine().addElement(
                TextElement(
                    text = line,
                    alignment = Alignment.CENTER
                )
            )
            page.addLine().addElement(
                TextElement(
                    text = "${getEmojiByUnicode(0x26A0)} Do Take CHECK-OUT SLIP ${
                        getEmojiByUnicode(0x26A0)
                    }", alignment = Alignment.CENTER
                )
            )
            page.addLine().addElement(
                TextElement(
                    text = "Parking at owners risk Lost Ticket Penalty Rs 50 Parking Charges as applicable Non-refundable & Non-Transferable",
                    alignment = Alignment.CENTER
                )
            )
            page.addLine().addElement(
                TextElement(
                    text = line,
                    alignment = Alignment.CENTER,
                    widthWeight = 1
                )
            )

            page.addLine()
                .addElement(TextElement(text = "powered By:", alignment = Alignment.CENTER))
            page.addLine()
                .addElement(
                    ImageElement(
                        image = icon,
                        height = 50,
                        widthWeight = 1,
                        shrinkToFit = true
                    )
                )
            page.addLine().addElement(TextElement(text = ""))
            page.addLine().addElement(TextElement(text = ""))
            page.addLine().addElement(TextElement(text = ""))
            return page
        } catch (e: Exception) {
            Log.i("PRINTING", "checkIn: ${e.localizedMessage}")
            return null
        }
    }

    private fun getBitmap(): Bitmap {
        val writer: Writer = QRCodeWriter()
        val bm = writer.encode(info, BarcodeFormat.QR_CODE, size, size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        for (i in 0 until size) {
            for (j in 0 until size) {
                bitmap.setPixel(i, j, if (bm[i, j]) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap.trimBorders(Color.WHITE)
    }
}

fun getEmojiByUnicode(unicode: Int) = String(Character.toChars(unicode))