package com.example.mytestingproject

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.text.method.ScrollingMovementMethod
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendStatus.movementMethod = ScrollingMovementMethod()

        checkPermission()
        sendMapFile().forEach { (phone, name) ->
            binding.sendStatus.append("$name and $phone\n\n")
        }
        binding.btnInfo.setOnClickListener {
            binding.sendStatus.text=""
            val intent = Intent(applicationContext, MainActivity::class.java)
            val pi: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
            val sms: SmsManager = SmsManager.getDefault()
            sendMapFile().forEach {(phone,name)->
                sms.sendTextMessage(phone, null, engMsg(), pi, null)
                sms.sendTextMessage(phone, null, hindiMsg(), pi, null)
                binding.sendStatus.append("$name and $phone status Send ${getEmojiByUnicode()}\n\n")
            }
        }
    }

    private fun checkPermission() {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.SEND_SMS),
                sendSms
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == sendSms) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(this, binding.root, "SMS PERMISSION GRANTED", Snackbar.LENGTH_SHORT)
                    .show()
                sendMapFile().forEach { (phone, name) ->
                    binding.sendStatus.append("$name and $phone\n\n")
                }
            } else {
                Snackbar.make(
                    this,
                    binding.root,
                    "SMS PERMISSION NOT GRANTED",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private val sendSms = 102
    private fun engMsg(): String {
        return "Hello Sir,\n" +
                "MyParkings/BECIL, Would like to Inform that, from the 1st of February 2023. The rental of POS/EDC devices will charged at Rs.530 incl. Taxes\n" +
                "If you have any questions or queries, please contact us @+919090808809.\n" +
                "Thanks & Regards,\n" +
                "@TeamMyParkings/BECIL"
    }

    private fun hindiMsg(): String {
        return "नमस्ते महोदय,\n" +
                "MyParkings/BECIL की ओर से हम आपको सूचित करना चाहते है की 1 फ़रवरी 2023 से POS devices का किराया, कर सहित 530 रुपए प्रति माह हो जाएगा | \n" +
                "अन्य किसी सहायता के लिए समपर्क करे  @9090808809\n" +
                "\n" +
                "धन्यवाद,\n" +
                "@TeamMyParkings/BECIL"
    }


    private fun getEmojiByUnicode(unicode: Int = 0x2714) = String(Character.toChars(unicode))
}