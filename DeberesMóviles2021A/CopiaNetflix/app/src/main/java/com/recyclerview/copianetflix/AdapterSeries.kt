package com.recyclerview.carouselviewexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jama.carouselviewexample.R

class AdapterSeries() : RecyclerView.Adapter<AdapterSeries.ViewHolder>(){
    lateinit var mySerieData: Array<Series>
    var context: Context? = null

    fun AdapterSeries(mySerieData: Array<Series>) {
        this.mySerieData = mySerieData
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var serieImage: ImageView
            var textViewName: TextView
            var textViewDate: TextView

            init {
                serieImage = itemView.findViewById(R.id.imageview)
                textViewName = itemView.findViewById(R.id.textName)
                textViewDate = itemView.findViewById(R.id.textdate)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view =
            layoutInflater.inflate(R.layout.movie_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myMovieDataList = mySerieData[position]
        holder.textViewName.text = myMovieDataList.serieName
        holder.textViewDate.text = myMovieDataList.serieDate
        holder.serieImage.setImageResource(myMovieDataList.serieImage!!)
        holder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                myMovieDataList.serieName,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return mySerieData.size
    }

}