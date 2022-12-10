package com.example.mytestingproject.fragment

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.mytestingproject.R
import com.example.mytestingproject.adaptor.ContentRecycleData
import com.example.mytestingproject.adaptor.InfoAdaptor
import com.example.mytestingproject.databinding.OneFragmentLayoutBinding
import com.example.mytestingproject.model.Content
import com.example.mytestingproject.utils.InfoList
import com.example.mytestingproject.utils.Ruppess
import com.example.mytestingproject.utils.msg
import com.example.mytestingproject.utils.setHtmlTxt


class OneFragment : Fragment(R.layout.one_fragment_layout) {

    private lateinit var binding: OneFragmentLayoutBinding
    private lateinit var contentAdaptor: ContentRecycleData
    private lateinit var infoAdaptor: InfoAdaptor

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OneFragmentLayoutBinding.bind(view)

        val text = "No Attestation"
        val ss = SpannableString(text)
        val boldSpan = StyleSpan(Typeface.BOLD)
        ss.setSpan(boldSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.itemOneTxt.text = ss
        binding.itemOneTxt.append(" ")
        binding.itemOneTxt.append(setHtmlTxt("& India visit needed", "#90a0bc"))
        binding.itemSecondTxt.text = "Start with as low as Rs. 100"

        binding.firstCompanyLayout.companyLogo.setImageResource(R.drawable.company)
        binding.firstCompanyLayout.titleCompany.text = "Shriram Transport Finance"
        binding.firstCompanyLayout.interestAmt.text = "8%p.a."
        binding.firstCompanyLayout.minimumInvestmentAmt.text = "$Ruppess 5,000"


        binding.secondCompanyLayout.companyLogo.setImageResource(R.drawable.mhaindra)
        binding.secondCompanyLayout.titleCompany.text = "Mahindra Finance"
        binding.secondCompanyLayout.interestAmt.text = "7 %p.a."
        binding.secondCompanyLayout.interestAmt.text = "Interest Rate"
        binding.secondCompanyLayout.minimumInvestmentAmt.text = "$Ruppess 5,000"
        setAdaptor()
        setInfoAdaptor()
        binding.contentRecycleView.addOnItemTouchListener(object : OnItemTouchListener {
            override fun onInterceptTouchEvent(view: RecyclerView, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> view.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(view: RecyclerView, event: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }

    private fun setInfoAdaptor() {
        binding.footerOptionRecycleView.apply {
            isNestedScrollingEnabled = false
            infoAdaptor = InfoAdaptor {
                activity?.msg(it)
            }
            adapter = infoAdaptor
        }
        infoAdaptor.submitList(InfoList)
    }

    private fun setAdaptor() {
        binding.contentRecycleView.apply {
            contentAdaptor = ContentRecycleData {
                activity?.msg("Contact me")
            }
            adapter = contentAdaptor
        }
        contentAdaptor.submitList(Content.list)
        binding.contentRecycleView.smoothScrollToPosition(3)
    }

}