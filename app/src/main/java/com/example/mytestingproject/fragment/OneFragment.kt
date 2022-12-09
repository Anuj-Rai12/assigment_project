package com.example.mytestingproject.fragment

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.OneFragmentLayoutBinding
import com.example.mytestingproject.utils.Ruppess
import com.example.mytestingproject.utils.setHtmlTxt


class OneFragment : Fragment(R.layout.one_fragment_layout) {

    private lateinit var binding: OneFragmentLayoutBinding
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= OneFragmentLayoutBinding.bind(view)

        val text = "No Attestation"
        val ss = SpannableString(text)
        val boldSpan = StyleSpan(Typeface.BOLD)
        ss.setSpan(boldSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.thirdHeadingTxt.text=ss
        binding.thirdHeadingTxt.append(" ")
        binding.thirdHeadingTxt.append(setHtmlTxt("& India visit needed","#90a0bc"))
        binding.fourthHeadingTxt.text="Start with as low as Rs. 100"

        binding.companyOne.companyLogo.setImageResource(R.drawable.company)
        binding.companyOne.titleCompany.text="Shriram Transport Finance"
        binding.companyOne.intrestAmt.text="8%p.a."
        binding.companyOne.manageAmtTxt.text="$Ruppess 5,000"


        binding.companyTwo.companyLogo.setImageResource(R.drawable.mhaindra)
        binding.companyTwo.titleCompany.text="Mahindra Finance"
        binding.companyTwo.intrestAmt.text="7%p.a."
        binding.companyTwo.intrestRate.text="Interest Rate"
        binding.companyTwo.manageAmtTxt.text="$Ruppess 5,000"

    }

}