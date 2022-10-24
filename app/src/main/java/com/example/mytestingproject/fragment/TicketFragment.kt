package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.TicketLayoutBinding

class TicketFragment:Fragment(R.layout.ticket_layout) {

    private lateinit var binding: TicketLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= TicketLayoutBinding.bind(view)



    }
}