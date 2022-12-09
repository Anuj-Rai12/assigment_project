package com.example.mytestingproject.model

import com.example.mytestingproject.R

data class Content(
    val id: Int,
    val title: String = ""
) {
    companion object {
        val list = listOf(
            Content(
                R.drawable.person_img,
                "I am an NRI FD Advisor, helped 1,000+ NRIs"
            ),
            Content(
                R.drawable.person_img,
                "I am an NRI FD Advisor, helped 1,000+ NRIs"
            ),
            Content(
                R.drawable.person_img,
                "I am an NRI FD Advisor, helped 1,000+ NRIs"
            ),
            Content(
                R.drawable.person_img,
                "I am an NRI FD Advisor, helped 1,000+ NRIs"
            ),
        )
    }
}
