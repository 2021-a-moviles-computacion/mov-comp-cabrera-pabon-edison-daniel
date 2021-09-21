package com.example.examen2b

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.examen2b.dto.FirebaseProductoDTO
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Geo : AppCompatActivity() {
    private lateinit var mapa: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo)
        val empleado = intent.getParcelableExtra<FirebaseProductoDTO>("producto")



        Log.i("RECIBIENDO EL EMPLEADO", "lATITUD ${empleado!!.longitud}")
        Log.i("RECIBIENDO EL EMPLEADO", "lONGITUD ${empleado!!.latitud}")

        var latitud = empleado!!.latitud.toString().toDouble()
        var longitUd = empleado!!.longitud.toString().toDouble()


        //Log.i("TREAR UBICACION", "LATITUD ${latitud}")
        //Log.i("TREAR UBICACION", "LONGITUD ${longitid}")

        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        fragmentoMapa.getMapAsync { googleMap ->
            if(googleMap != null){
                //val quicentro = LatLng(-0.176125, -78.480208)
                val quicentro = LatLng(latitud, longitUd)
                mapa = googleMap
                establecerConfiguracionMapa()
                anadirMarcador(quicentro, "UBICACION")
                moverCamara(quicentro, 17f)
            }
        }
    }


    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }

            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun anadirMarcador(latLng: LatLng, title: String){
        mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun moverCamara (latLng: LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }
}