package com.tsu.myfirstapplication

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.tsu.myfirstapplication.databinding.ActivityMainBinding
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2

    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dictionary, R.id.navigation_training, R.id.navigation_video
            )
        )

        mediaPlayer = MediaPlayer()

        navView.setupWithNavController(navController)
    }
    fun playAudio(url: String){
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch(e: Exception) {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Audio can't be played")
            dialogBuilder.setMessage("Check your internet connection")
            dialogBuilder.show()
        }
    }
}