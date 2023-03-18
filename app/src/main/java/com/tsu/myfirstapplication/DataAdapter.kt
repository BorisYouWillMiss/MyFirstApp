package com.tsu.myfirstapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsu.myfirstapplication.models.MeaningModel
import com.tsu.myfirstapplication.models.MeaningCardModel

class DataAdapter constructor() : RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder>() {

    private val adapterData = ArrayList<MeaningCardModel>()

    class DataAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindCardInfo(item : MeaningCardModel){
            itemView.findViewById<TextView>(R.id.definitionText).text = item.definition

            if(item.example.length != 1){
                itemView.findViewById<TextView>(R.id.exampleText).text = item.example
            } else {
                itemView.findViewById<TextView>(R.id.exampleText).visibility = View.GONE
            }
        }
    }

    fun setData(data: List<MeaningCardModel>){
        adapterData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = adapterData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_card, parent, false)

        return DataAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        holder.bindCardInfo(adapterData[position])
    }
}