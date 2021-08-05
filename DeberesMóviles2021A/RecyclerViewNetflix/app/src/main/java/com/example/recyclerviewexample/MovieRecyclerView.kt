package com.example.recyclerviewexample

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieRecyclerView (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieImage: ImageView
        var textViewName: TextView
        var textViewDate: TextView

        init {
            movieImage = itemView.findViewById(R.id.imageview)
            textViewName = itemView.findViewById(R.id.textName)
            textViewDate = itemView.findViewById(R.id.textdate)
        }
}
