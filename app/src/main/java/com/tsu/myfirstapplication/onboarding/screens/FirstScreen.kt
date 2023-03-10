package com.tsu.myfirstapplication.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tsu.myfirstapplication.R
import com.tsu.myfirstapplication.databinding.FragmentFirstScreenBinding

class FirstScreen : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}