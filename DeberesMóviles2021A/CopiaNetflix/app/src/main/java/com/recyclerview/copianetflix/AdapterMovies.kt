package com.recyclerview.copianetflix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterMovies(var myMovieData: Array<Movies>, val listener: MoviesListener) : RecyclerView.Adapter<AdapterMovies.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var movieImage: ImageView
            var textViewName: TextView
            var textViewDate: TextView

            init {
                movieImage = itemView.findViewById(R.id.imageviewMovie)
                textViewName = itemView.findViewById(R.id.textNameMovie)
                textViewDate = itemView.findViewById(R.id.textDateMovie)
            }
        fun bind(pictureMovie: Movies, listener: MoviesListener){
            textViewName = itemView.findViewById(R.id.textNameMovie)
            textViewName.setOnClickListener { listener.onItemClick(pictureMovie.toString())}
        }

    }
    interface MoviesListener{

        fun onItemClick(movies: String)
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
        holder.bind(myMovieData[position], listener)
    }



    override fun getItemCount(): Int {
        return myMovieData.size
    }


}