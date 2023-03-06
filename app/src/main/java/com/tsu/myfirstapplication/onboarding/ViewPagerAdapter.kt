package com.tsu.myfirstapplication.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tsu.myfirstapplication.onboarding.screens.FirstScreen
import com.tsu.myfirstapplication.onboarding.screens.SecondScreen
import com.tsu.myfirstapplication.onboarding.screens.ThirdScreen

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment{
        return when(position){
            0 -> {FirstScreen()}
            1 -> {SecondScreen()}
            2 -> {ThirdScreen()}
            else -> {FirstScreen()}
        }
    }


}