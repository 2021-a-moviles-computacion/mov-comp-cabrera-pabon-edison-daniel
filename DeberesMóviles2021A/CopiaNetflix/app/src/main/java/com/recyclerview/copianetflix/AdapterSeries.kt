package com.recyclerview.copianetflix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterSeries(var mySerieData: Array<Series>, val listener: SeriesListener) : RecyclerView.Adapter<AdapterSeries.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var serieImage: ImageView
        var textViewName: TextView
        var textViewDate: TextView

        init {
            serieImage = itemView.findViewById(R.id.imageviewSerie)
            textViewName = itemView.findViewById(R.id.textNameSerie)
            textViewDate = itemView.findViewById(R.id.textDateSerie)
        }
        fun bind(pictureSerie: Series, listener: SeriesListener){
            textViewName = itemView.findViewById(R.id.textNameSerie)
            textViewName.setOnClickListener { listener.onItemClick(pictureSerie.toString())}
        }
    }

    interface SeriesListener{

        fun onItemClick(series: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view =
            layoutInflater.inflate(R.layout.serie_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mySerieDataList = mySerieData[position]
        holder.textViewName.text = mySerieDataList.serieName
        holder.textViewDate.text = mySerieDataList.serieDate
        holder.serieImage.setImageResource(mySerieDataList.serieImage!!)
        holder.bind(mySerieData[position], listener)
    }

    override fun getItemCount(): Int {
        return mySerieData.size
    }

}