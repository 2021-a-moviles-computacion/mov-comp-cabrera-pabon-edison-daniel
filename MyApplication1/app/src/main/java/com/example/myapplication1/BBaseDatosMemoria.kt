package com.example.myapplication1

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador
                .add(BEntrenador("Adrian","a@a.com")
                )
            arregloBEntrenador
                .add(
            BEntrenador("Vicente","v@a.com")
                )
            arregloBEntrenador
                .add(
            BEntrenador("Carolina","c@a.com")
                )
        }
    }
}