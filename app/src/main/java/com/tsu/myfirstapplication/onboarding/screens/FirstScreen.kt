package com.tsu.myfirstapplication.onboarding.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.viewpager2.widget.ViewPager2
import com.tsu.myfirstapplication.R
import com.tsu.myfirstapplication.ViewPagerActivity
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

        binding.next1.setOnClickListener {
            Log.d("FirstFragment", "test button")
            viewPager?.setCurrentItem(1, true)
        }

        binding.skip1.setOnClickListener {
            (activity as ViewPagerActivity).goToSignup()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}