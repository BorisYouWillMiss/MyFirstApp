package com.tsu.myfirstapplication.ui.Tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tsu.myfirstapplication.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.webview.loadUrl("https://learnenglish.britishcouncil.org/general-english/video-zone")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}