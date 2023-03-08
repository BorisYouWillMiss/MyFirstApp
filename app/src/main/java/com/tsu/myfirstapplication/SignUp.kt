package com.tsu.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tsu.myfirstapplication.databinding.ActivitySignUpBinding
import androidx.appcompat.app.AlertDialog
class SignUp : AppCompatActivity() {
    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener{
            Log.d("Tagtag", "Button pressed")
            val intent = Intent(this@SignUp, ViewPagerActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener{
            if (binding.editEmailField.text.toString().isNotEmpty() &&
                    binding.editNameField.text.toString().isNotEmpty() &&
                    binding.editPasswordField.text.toString().isNotEmpty()){
                val intent = Intent(this@SignUp, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Signing up failed")
                dialogBuilder.setMessage("All fields must be filled")
                dialogBuilder.show()
            }
        }


    }
}