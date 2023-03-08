package com.tsu.myfirstapplication.ui.Tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.tsu.myfirstapplication.databinding.FragmentVideoBinding
import android.webkit.WebView
import android.webkit.WebResourceRequest
import android.net.Uri

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private class ClientWebView : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            if(Uri.parse(request?.url.toString()).toString().contains("https://learnenglish.britishcouncil.org/general-english/video-zone", ignoreCase = true)) {
                return super.shouldOverrideUrlLoading(view, request)
            }
            return true
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.webview.webViewClient = ClientWebView()
        binding.webview.loadUrl("https://learnenglish.britishcouncil.org/general-english/video-zone")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}