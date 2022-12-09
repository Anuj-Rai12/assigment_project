package com.example.mytestingproject.adaptor

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter



class MainViewPager : FragmentStateAdapter {

    constructor(fragment: Fragment) : super(fragment)

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    private val getTotalFragment: MutableList<Fragment> = mutableListOf()


    override fun getItemCount(): Int {
        return getTotalFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return getTotalFragment[position]
    }

    fun setFragment(fragment: Fragment) {
        getTotalFragment.add(fragment)
        Log.i("MainViewPager", "setFragment: $getTotalFragment")
    }

    fun removedFragment(position: Int) {
        getTotalFragment.removeAt(position)
        Log.i("MainViewPager", "removedFragment: $getTotalFragment")
    }

    fun getSize(): Int {
        Log.i("MainViewPager", "getSize: ${getTotalFragment.size}")
        return getTotalFragment.size
    }

}