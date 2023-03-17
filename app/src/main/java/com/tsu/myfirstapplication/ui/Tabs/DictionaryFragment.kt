package com.tsu.myfirstapplication.ui.Tabs

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsu.myfirstapplication.databinding.FragmentDictionaryBinding
import androidx.recyclerview.widget.RecyclerView
import com.tsu.myfirstapplication.models.WordModel
import com.tsu.myfirstapplication.DataAdapter
import com.tsu.myfirstapplication.RetrofitApi
import com.tsu.myfirstapplication.databinding.WordCardBinding
import com.tsu.myfirstapplication.models.MeaningCardModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = dataAdapter
        }

        var testList: ArrayList<MeaningCardModel> = ArrayList<MeaningCardModel>()
        testList.add(MeaningCardModel("Def1", "example1"))
        testList.add(MeaningCardModel("Def2", "example2"))
        dataAdapter.setData(testList)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}