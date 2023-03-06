package com.tsu.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tsu.myfirstapplication.onboarding.ViewPagerAdapter
import android.content.Intent


class ViewPagerActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_view_pager)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
    }

    fun goToSignup(){
        val intent = Intent(this@ViewPagerActivity, SignUp::class.java)
        startActivity(intent)
    }
}