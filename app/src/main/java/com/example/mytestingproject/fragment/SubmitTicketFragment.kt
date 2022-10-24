package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.SubmitTicketLayoutBinding

class SubmitTicketFragment :Fragment(R.layout.submit_ticket_layout) {

    private lateinit var binding: SubmitTicketLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= SubmitTicketLayoutBinding.bind(view)



    }
}