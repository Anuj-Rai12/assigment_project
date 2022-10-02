package com.example.mytestingproject.fragment

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mytestingproject.MainActivity
import com.example.mytestingproject.R
import com.example.mytestingproject.TwoActivity
import com.example.mytestingproject.databinding.TwoFragmentBinding
import com.example.mytestingproject.utils.msg


class TwoFragment : Fragment(R.layout.two_fragment) {
    private lateinit var binding: TwoFragmentBinding
    private val args: TwoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TwoFragmentBinding.bind(view)
        binding.titleTxt.text = args.title
        binding.btnCreate.setOnClickListener {
            setIcon()
        }
    }

    private fun setIcon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = activity?.getSystemService(ShortcutManager::class.java)
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("ID_ONE", "Three Fragment Open done")
                intent.action = Intent.ACTION_MAIN
                val pinShortCutsInfo =
                    ShortcutInfo.Builder(activity, "Short_cut_manger_id_121")
                        .setShortLabel("anuj")
                        .setIcon(
                            Icon.createWithResource(
                                activity,
                                R.drawable.act_stories_icon
                            )
                        )
                        .setIntent(intent)
                        .build()

                val pinnedPositionsIntent =
                    shortcutManager.createShortcutResultIntent(pinShortCutsInfo)

                val successCallback =
                    PendingIntent.getBroadcast(activity, 0, pinnedPositionsIntent, 0)

                shortcutManager.requestPinShortcut(pinShortCutsInfo, successCallback.intentSender)

            }
        } else {
            activity?.msg("Cannot set it")
        }
    }

}