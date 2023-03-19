package com.tsu.myfirstapplication.ui.Tabs

import android.R
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsu.myfirstapplication.databinding.FragmentDictionaryBinding
import androidx.recyclerview.widget.RecyclerView
import com.tsu.myfirstapplication.models.WordModel
import com.tsu.myfirstapplication.DataAdapter
import com.tsu.myfirstapplication.MainActivity
import com.tsu.myfirstapplication.RetrofitApi
import com.tsu.myfirstapplication.localDB.WordWithDefinitions
import com.tsu.myfirstapplication.models.MeaningCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null
    private lateinit var recView: RecyclerView
    private lateinit var data: List<WordModel>
    private val dataAdapter: DataAdapter by lazy {
        DataAdapter()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    val api = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

    fun getWord(theWord: String){
        var word: String = ""
        var phonetic : String = ""
        var audio : String = ""
        var vna : String = ""
        var meanings: ArrayList<MeaningCardModel> = ArrayList<MeaningCardModel>()

        GlobalScope.launch(Dispatchers.IO) {
            Log.d("Debugging", "Launched coroutine")
            val response = api.word(theWord).awaitResponse()
            if (response.isSuccessful) {
                Log.d("Debugging", "Response successful")
                data = response.body()!!

                word = data[0].word
                phonetic = if(!data[0].phonetic.isNullOrEmpty()) data[0].phonetic
                else ""

                if(phonetic == "" || audio == "") {
                    if (data[0].phonetics.size != 0) {
                        for (i in data[0].phonetics.indices) {
                            if(!data[0].phonetics[i].text.isNullOrEmpty()) {
                                phonetic = data[0].phonetics[i].text
                            }
                            if(!data[0].phonetics[i].audio.isNullOrEmpty()) {
                                audio = data[0].phonetics[i].audio
                            }
                            if (phonetic != "" && audio != "") break
                        }
                    }
                }
                // vna = verb/noun/adjective = partOfSpeech
                vna = data[0].meanings[0].partOfSpeech

                for(k in data[0].meanings[0].definitions.indices) {
                    var def: String = data[0].meanings[0].definitions[k].definition
                    var example: Spannable = SpannableString("t")

                    if(!data[0].meanings[0].definitions[k].example.isNullOrEmpty()){
                        val spannable: Spannable = SpannableString("Example: " + data[0].meanings[0].definitions[k].example)
                        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#65AAEA")), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        example = spannable
                    }

                    meanings.add(MeaningCardModel(def,
                        example))
                }

            }
            else {
                activity?.runOnUiThread(java.lang.Runnable {
                    val dialogBuilder = AlertDialog.Builder(activity as MainActivity)
                    dialogBuilder.setTitle("Word not found")
                    dialogBuilder.setMessage("No such word in the vocabulary")
                    dialogBuilder.show()
                })
            }
            activity?.runOnUiThread(java.lang.Runnable {
                setCardValues(word, phonetic, audio, vna, meanings)
                soundButtonSet(audio)
            })
        }


    }

    fun setCardValues(word : String, phonetic : String, audio : String, vna : String, meanings : ArrayList<MeaningCardModel>){
        binding.theWord.text = word
        binding.transcriptionText.text = phonetic
        binding.partOfSpeechText.text = vna
        dataAdapter.setData(meanings)
    }

    fun soundButtonSet(audio : String) {
        binding.imageButton.setOnClickListener {
            (context as MainActivity).playAudio(audio)
        }
    }

    fun getWordFromDatabase(wordWithDefinitions : List<WordWithDefinitions>){
        GlobalScope.launch(Dispatchers.IO) {

            var meanings: ArrayList<MeaningCardModel> = ArrayList<MeaningCardModel>()

            var word: String = wordWithDefinitions[0].Word.word
            var phonetic: String  = if(!wordWithDefinitions[0].Word.phonetics.isNullOrEmpty()) wordWithDefinitions[0].Word.phonetics
            else ""
            var audio: String = if (!wordWithDefinitions[0].Word.audio.isNotEmpty()) wordWithDefinitions[0].Word.audio
            else ""

            var vna: String = wordWithDefinitions[0].Word.partOfSpeech

            for(k in wordWithDefinitions[0].definitions.indices) {
                var def: String = wordWithDefinitions[0].definitions[k].definition
                var example: Spannable = SpannableString("t")

                if(!wordWithDefinitions[0].definitions[k].example.isNullOrEmpty()){
                    val spannable: Spannable = SpannableString("Example: " + wordWithDefinitions[0].definitions[k].example)
                    spannable.setSpan(ForegroundColorSpan(Color.parseColor("#65AAEA")), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    example = spannable
                }

                meanings.add(MeaningCardModel(def, example))
            }

            activity?.runOnUiThread(java.lang.Runnable {
                setCardValues(word, phonetic, audio, vna, meanings)
                soundButtonSet(audio)
            })

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = dataAdapter
        }

        binding.btnAddWord.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                (activity as MainActivity).saveWord(data)
            }
        }

        binding.editNameField.setOnKeyListener(View.OnKeyListener {
                v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (!binding.editNameField.text.toString().isNullOrEmpty()) {

                    if ((activity as MainActivity).checkForInternet()) {
                        viewLifecycleOwner.lifecycleScope.launch {
                            getWord(binding.editNameField.text.toString())
                        }
                    } else {
                        viewLifecycleOwner.lifecycleScope.launch {
                            var word =
                                (activity as MainActivity).dao.getWordWithDefinitions(binding.editNameField.text.toString())
                            if (word.isEmpty()) {
                                val dialogBuilder = AlertDialog.Builder(activity as MainActivity)
                                dialogBuilder.setTitle("Word not found")
                                dialogBuilder.setMessage("No such word in database")
                                dialogBuilder.show()
                            } else {
                                getWordFromDatabase(word)
                            }
                        }
                    }
                }
                return@OnKeyListener true
            }
            false
        })
        // Test data
        var testList: ArrayList<MeaningCardModel> = ArrayList<MeaningCardModel>()
        dataAdapter.setData(testList)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}