package com.tsu.myfirstapplication


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.tsu.myfirstapplication.onboarding.ViewPagerAdapter
import com.tsu.myfirstapplication.databinding.FragmentViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = FragmentViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<Button>(R.id.buttonskip).setOnClickListener {
            Log.d("TestTest", "skip clicked")
            goToSignup()
        }

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)

        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                curPageDotSet(position)
                boardButtonSet(position)
            }
        })


    }

    fun boardButtonSet(position: Int){
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val boardButton = findViewById<Button>(R.id.boardButton)
        when(position){
            0->{
                boardButton.setText("Next")
                boardButton.setOnClickListener {
                    viewPager?.setCurrentItem(1, true)
                }
            }
            1->{
                boardButton.setText("Next")
                boardButton.setOnClickListener {
                    viewPager?.setCurrentItem(2, true)
                }
            }
            2->{
                boardButton.setText("Let's Start")
                boardButton.setOnClickListener {
                    goToSignup()
                }
            }
        }
    }

    fun curPageDotSet(position: Int){
        val pager1 = findViewById<ImageView>(R.id.pager1)
        val pager2 = findViewById<ImageView>(R.id.pager2)
        val pager3 = findViewById<ImageView>(R.id.pager3)

        val uri1 = "@drawable/_current" // where myresource (without the extension) is the file
        val uri2 = "@drawable/_not_current" // where myresource (without the extension) is the file
        val imageResource1 = resources.getIdentifier(uri1, null, packageName)
        val imageResource2 = resources.getIdentifier(uri2, null, packageName)

        val res1 = resources.getDrawable(imageResource1)
        val res2 = resources.getDrawable(imageResource2)

        when(position){
            0->{ pager1.setImageDrawable(res1)
               pager2.setImageDrawable(res2)
                pager3.setImageDrawable(res2)
            }
            1->{ pager1.setImageDrawable(res2)
                pager2.setImageDrawable(res1)
                pager3.setImageDrawable(res2)
            }
            2->{ pager1.setImageDrawable(res2)
                pager2.setImageDrawable(res2)
                pager3.setImageDrawable(res1)
            }
        }
    }

    fun goToSignup(){
        val intent = Intent(this@ViewPagerActivity, SignUp::class.java)
        startActivity(intent)
    }
}