package com.recyclerview.copianetflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.jama.copianetflix.enums.IndicatorAnimationType
import com.jama.copianetflix.enums.OffsetType
import kotlinx.android.synthetic.main.activity_movies_carousel.*

class VistaSerie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_carousel)

        val images = arrayListOf(R.drawable.fear_walking_dead_2015,R.drawable.the_walking_dead_2012, R.drawable.fear_walking_dead_2015)

        carouselView.apply {
            size = images.size
            autoPlay = true
            autoPlayDelay = 3000
            resource = R.layout.center_carousel_item
            indicatorAnimationType = IndicatorAnimationType.THIN_WORM
            carouselOffset = OffsetType.CENTER
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imageView)
                imageView.setImageDrawable(resources.getDrawable(images[position]))
            }
            show()
        }

    }
}

