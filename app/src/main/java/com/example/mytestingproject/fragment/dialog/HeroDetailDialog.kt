package com.example.mytestingproject.fragment.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import coil.load
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.AvengerDialogLayoutBinding
import com.example.mytestingproject.model.MarvelHeroesItem
import com.example.mytestingproject.utils.setHtmlTxt


class HeroDetailDialog(context: Context) {

    private val dialog = Dialog(context)

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    @SuppressLint("SetTextI18n")
    fun showDialogDetail(data: MarvelHeroesItem, activity: Activity) {
        val binding = AvengerDialogLayoutBinding.inflate(LayoutInflater.from(activity))
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.img.load(data.imageurl) {
            crossfade(true)
            placeholder(R.drawable.loading)
        }
        binding.heroTxt.append(data.name)
        binding.heroInfoTxt.apply {
            text = "By : "
            append(setHtmlTxt(data.realname, "'#F44336'"))
            append("\t")
            append(" Team : ")
            append(setHtmlTxt(data.team, "'#F44336'"))
            append("\t")
            append("FirstAppearance : ")
            append(setHtmlTxt(data.firstappearance, "'#F44336'"))
            append("\t")
            append(" CreatedBy : ")
            append(setHtmlTxt(data.createdby, "'#F44336'"))
            append("\t")
            append("Publisher : ")
            append(setHtmlTxt(data.publisher, "'#F44336'"))
            append("\n")
        }
        binding.bioTxt.text = data.bio
        binding.bioTxt.movementMethod = ScrollingMovementMethod()
        binding.closeBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}