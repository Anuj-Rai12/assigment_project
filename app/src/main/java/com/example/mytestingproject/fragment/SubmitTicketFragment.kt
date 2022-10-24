package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.SubmitTicketLayoutBinding
import com.example.mytestingproject.setLogCat
import com.example.mytestingproject.toastMsg
import com.zoho.desk.asap.api.ZDPortalCallback
import com.zoho.desk.asap.api.ZDPortalException
import com.zoho.desk.asap.api.response.Ticket
import com.zoho.desk.asap.asap_tickets.ZDPortalSubmitTicket

class SubmitTicketFragment : Fragment(R.layout.submit_ticket_layout) {

    private lateinit var binding: SubmitTicketLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SubmitTicketLayoutBinding.bind(view)

        ZDPortalSubmitTicket.show(activity,object : ZDPortalCallback.CreateTicketCallback{
            override fun onException(exp: ZDPortalException?) {
                activity?.toastMsg("Error")
                setLogCat("SubmitTicket",exp?.errorMsg?:"Unknown Error")
            }
            override fun onTicketCreated(ticket: Ticket?) {
                activity?.toastMsg("Ticket")
                setLogCat("SubmitTicket", ticket?.id?:" Unknown Ticket ticketId")
            }
        })


    }
}