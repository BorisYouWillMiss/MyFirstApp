package com.tsu.myfirstapplication.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tsu.myfirstapplication.R
import com.tsu.myfirstapplication.ViewPagerActivity
import com.tsu.myfirstapplication.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next2.setOnClickListener{
            viewPager?.setCurrentItem(2, true)
        }

        binding.skip2.setOnClickListener {
            (activity as ViewPagerActivity).goToSignup()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}