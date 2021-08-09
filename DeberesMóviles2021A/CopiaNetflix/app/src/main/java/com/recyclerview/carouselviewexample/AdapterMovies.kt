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

class AdapterMovies() : RecyclerView.Adapter<AdapterMovies.ViewHolder>(){
    lateinit var myMovieData: Array<Movies>
    var context: Context? = null

    fun AdapterMovies(myMovieData: Array<Movies>) {
        this.myMovieData = myMovieData
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var movieImage: ImageView
            var textViewName: TextView
            var textViewDate: TextView

            init {
                movieImage = itemView.findViewById(R.id.imageview)
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
        val myMovieDataList = myMovieData[position]
        holder.textViewName.text = myMovieDataList.movieName
        holder.textViewDate.text = myMovieDataList.movieDate
        holder.movieImage.setImageResource(myMovieDataList.movieImage!!)
        holder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                myMovieDataList.movieName,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return myMovieData.size
    }

}