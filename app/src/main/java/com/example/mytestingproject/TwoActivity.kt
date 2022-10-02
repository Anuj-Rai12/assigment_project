package com.example.mytestingproject


import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.mytestingproject.databinding.TwoActivityBinding
import com.example.mytestingproject.utils.msg


class TwoActivity : AppCompatActivity() {
    private val binding by lazy {
        TwoActivityBinding.inflate(layoutInflater)
    }

    @SuppressLint("WrongThread")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val op = intent.getStringExtra("ID_ONE")
        if (op != null)
            msg(op.toString())

        binding.btnClick.setOnClickListener {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            if (shortcutManager.isRequestPinShortcutSupported) {
                val intent = Intent(applicationContext, TwoActivity::class.java)
                intent.putExtra("ID_ONE", "Hello ji Ji")
                intent.action = Intent.ACTION_MAIN
                val pinShortCutsInfo =
                    ShortcutInfo.Builder(applicationContext, "Short_cut_manger_id_121")
                        .setShortLabel("anuj")
                        .setIcon(Icon.createWithResource(applicationContext, R.drawable.act_stories_icon))
                        .setIntent(intent)
                        .build()

                val pinnedPositionsIntent = shortcutManager.
                createShortcutResultIntent(pinShortCutsInfo)

                val successCallback = PendingIntent.
                getBroadcast(this, 0, pinnedPositionsIntent, 0)

                shortcutManager.requestPinShortcut(pinShortCutsInfo, successCallback.intentSender)

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    private fun getLabelList() {
        //Success
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        // Defining a shortcut, Shortcut 1
        val shortcut1 = ShortcutInfo.Builder(applicationContext, "ID1")
            .setShortLabel("Instagram")
            .setIcon(Icon.createWithResource(applicationContext, R.drawable.act_stories_icon))
            .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com")))
            .build()

        // Defining a shortcut, Shortcut 2
        val shortcut2 = ShortcutInfo.Builder(applicationContext, "ID2")
            .setShortLabel("AskFM")
            .setIcon(Icon.createWithResource(applicationContext, R.drawable.act_stories_icon))
            .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ask.fm")))
            .build()

        //This Should be on Bg thread !!
        shortcutManager!!.dynamicShortcuts = listOf(shortcut1, shortcut2)
    }

    private fun anotherShortCut() {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(applicationContext)) {
            val shortcutInfo = ShortcutInfoCompat.Builder(applicationContext, "#1")
                .setIntent(
                    Intent(
                        applicationContext,
                        TwoActivity::class.java
                    ).setAction(Intent.ACTION_MAIN)
                ) // !!! intent's action must be set on oreo
                .setShortLabel("Test")
                .setIcon(
                    IconCompat.createWithResource(
                        applicationContext,
                        R.drawable.act_stories_icon
                    )
                )
                .build()
            ShortcutManagerCompat.requestPinShortcut(applicationContext, shortcutInfo, null)
        } else {
            msg("launcher does not support short cut icon")
        }
    }

    private fun addShortcut() {
        //Adding shortcut for MainActivity
        //on Home screen
        val shortcutIntent = Intent(
            applicationContext,
            TwoActivity::class.java
        )
        shortcutIntent.action = Intent.ACTION_MAIN
        val addIntent = Intent()
        addIntent
            .putExtra(Intent.ACTION_CREATE_SHORTCUT, shortcutIntent)
        addIntent.putExtra(Intent.ACTION_CREATE_SHORTCUT, "HelloWorldShortcut")
        addIntent.putExtra(
            Intent.ACTION_CREATE_SHORTCUT,
            Intent.ShortcutIconResource.fromContext(
                applicationContext,
                R.drawable.act_stories_icon
            )
        )
        addIntent.action = "com.android.launcher.action.INSTALL_SHORTCUT"
        //addIntent.putExtra("duplicate", false) //may it's already there so don't duplicate
        applicationContext.sendBroadcast(addIntent)
        msg("Added shortCut")
    }

}