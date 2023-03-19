package com.tsu.myfirstapplication

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.tsu.myfirstapplication.databinding.ActivityMainBinding
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AlertDialog
import com.tsu.myfirstapplication.localDB.DefinitionEntity
import com.tsu.myfirstapplication.localDB.DictionaryDatabase
import com.tsu.myfirstapplication.localDB.WordDao
import com.tsu.myfirstapplication.localDB.WordEntity
import com.tsu.myfirstapplication.models.WordModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mediaPlayer: MediaPlayer
    lateinit var dao: WordDao
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

        dao = DictionaryDatabase.getInstance(this).wordDao


        mediaPlayer = MediaPlayer()

        navView.setupWithNavController(navController)
    }

    fun checkForInternet(): Boolean{
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    suspend fun saveWord(wordData: List<WordModel>) {
        var word: String = wordData[0].word
        var phonetic: String = if(!wordData[0].phonetic.isNullOrEmpty()) wordData[0].phonetic
        else ""
        var audio: String = ""
        var vna: String = wordData[0].meanings[0].partOfSpeech

        if(phonetic == "" || audio == "") {
            if (wordData[0].phonetics.size != 0) {
                for (i in wordData[0].phonetics.indices) {
                    if(!wordData[0].phonetics[i].text.isNullOrEmpty()) {
                        phonetic = wordData[0].phonetics[i].text
                    }
                    if(!wordData[0].phonetics[i].audio.isNullOrEmpty()) {
                        audio = wordData[0].phonetics[i].audio
                    }
                    if (phonetic != "" && audio != "") break
                }
            }
        }

        for(k in wordData[0].meanings[0].definitions.indices){
            var def: String = wordData[0].meanings[0].definitions[k].definition
            var example: String = ""

            if(!wordData[0].meanings[0].definitions[k].example.isNullOrEmpty()){
                example = "Example: " + wordData[0].meanings[0].definitions[k].example
            }

            dao.insertDefinition(DefinitionEntity(0, word, def, example))
        }
        dao.insertWord(WordEntity(word, vna, audio, phonetic))
    }
    fun playAudio(url: String){
        if (checkForInternet()) {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            try {
                mediaPlayer.reset()
                mediaPlayer.setDataSource(url)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch(e: Exception) {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Audio can't be played")
                dialogBuilder.setMessage("No audio")
                dialogBuilder.show()
            }
        } else {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Audio can't be played")
            dialogBuilder.setMessage("No internet connection")
            dialogBuilder.show()
        }
    }
}