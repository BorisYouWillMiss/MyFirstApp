package com.tsu.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.tsu.myfirstapplication.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<Button>(R.id.buttonBack).setOnClickListener{
            Log.d("Tagtag", "Button pressed")
            val intent = Intent(this@SignUp, ViewPagerActivity::class.java)
            startActivity(intent)
        }

    }
}