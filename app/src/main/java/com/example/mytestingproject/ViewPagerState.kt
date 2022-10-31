package com.example.mytestingproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerStateAdaptor(fragment: FragmentManager) : FragmentPagerAdapter(fragment) {

    private val arrayList= mutableListOf<Fragment>()

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return arrayList[position]
    }

    fun addFragment(fragment: Fragment){
        arrayList.add(fragment)
    }
}