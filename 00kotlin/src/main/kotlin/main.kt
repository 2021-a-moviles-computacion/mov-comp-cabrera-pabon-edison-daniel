import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hola Mundo")
    //Tipo nombre=valor
    //Int edad=12;
    var edadProfesor = 32
    //var edadProfesor: Int = 32

    //Duck Typing
    var sueldoProfesor: Double = 1.23
    edadProfesor = 25.5.toInt()

    //Mutables
    var edadCachorro: Int = 0
    edadCachorro = 1
    edadCachorro = 2
    edadCachorro = 3

    //Inmutables
    val numeroCedula = 1725866170
    //numeroCedula = 12345678

    //Tipos de variables(JAVA)
    val nombreProfesor:String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()

    /*if (condicion){

    }
    else{

    }*/
    //switch Estado ->S->C
    /*val estadoCivil: String = "S"

    when (estadoCivil){
        ("S") -> {
            prinln("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> prinln("NO Reconocido")
    }*/

    imprimirNombre("Edison")
    calcularSueldo(1000.00)
    calcularSueldo(1000.00, 14.00)

    calcularSueldo(
        bonoEspecial = 15.00,
        //tasa = 12.00
        sueldo = 150.00,
    )
    calcularSueldo(
        tasa = 14.00,
        bonoEspecial = 30.00,
        sueldo = 1500.00
    )

    //Arreglos
    //Estáticos
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    //arregloEstatico.add(12) -> NO TENEMOS AQUI, NO SE PUEDE
                                // MODIFICAR LOS ELEMENTOS DEL ARREGLO
    println(arregloEstatico)

    //Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)


    //Métodos de Recorrido de Arreglos
    //For each -> unit
    //iterar arreglo

    val respuestaForeach: Unit = arregloDinamico
        .forEach{
            //it: Int -> El valor o los valores que van a llegar a esta función
            //    si solamente se recibe 1 parametro, este se va a llamar "it".
            println("Valor Actual: ${it}")

        }
    //println(respuestaForeach)

  arregloDinamico
        .forEach{ valorActual: Int ->
            println("Valor Actual: ${valorActual}")
        }


    arregloDinamico
        .forEachIndexed{ indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)

    val respuestaMap2 = arregloDinamico.map { it + 15}
        /*.map{ valorActual: Int ->
            return@map valorActual + 15
        }*/
    println(respuestaMap2)

    val respuestasFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
        val mayoresCinco: Boolean = valorActual > 5 //Expresión Condición
        return@filter mayoresCinco
        }

    val respuestaFilter2 = arregloDinamico.filter { it <= 5}

    println(respuestasFilter)
    println(respuestaFilter2)
}

fun imprimirNombre(nombre: String):Unit {
println("Nombre: ${nombre}")
}

fun calcularSueldo(
sueldo: Double, //parámetro requerido
tasa: Double = 12.00, //Opcional(valor por defecto)
bonoEspecial: Double? = null
//bonoEspecial: Double?,
):Double{
if (bonoEspecial == null) { //indentar -> ctrl + a -> ctrl +a+l
 return sueldo * (100 / 14)
}
else{
return sueldo * (100 / 14) + bonoEspecial
}

}