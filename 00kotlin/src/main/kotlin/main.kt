import java.util.*
import kotlin.collections.ArrayList

fun main() {
    //println("Hola Mundo")
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

    //imprimirNombre("Edison")
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
    /*println(arregloDinamico)
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
println("Nombre: ${nombre}")*/


    //REDUCE -> VALOR ACUMULADO
    // 1) Devuelve el acumulado => 0
    // 2) En que valor empieza => 0
    // [1,2,3,4,5]
    //valorIteración = valorEmpieza +1 -> iteración 1
    //3 = valorIteración1 + 2 = 1+2 = acumulado -> iteración2
    //6 = valorIteración3 + 3 = 3+3 = acumulado -> iteración3
    //10 = valorIteración4 + 4 = 6+3 = acumulado -> iteración4
    //16 = valorIteración1 + 5 = 10+5 = acumulado -> iteración5
    // Ultimo Acumulado

    val respuestasReduce: Int = arregloDinamico
        .reduce{ //acumulado = 0 -> siempre empieza en cero
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    //println(respuestasReduce) // 28

    val arregloDanio = arrayListOf<Int>(12,5,8,10)
    val respuestaReduceFold = arregloDanio
        .fold(
             100,
            { acumulado, valorActualIteration ->
                return@fold acumulado - valorActualIteration
            }
        )
    //println(respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map { it *2.3 }//arreglo
        .filter { it > 20 }//arreglo
        .fold(100.00, {acc, i -> acc -i})//valor
        .also { println(it) }// ejecutar codigo extra
   //println("Valor vida actual ${vidaActual}")

   val ejemploUno = Suma(1, 2)
    //val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null, 2)
    //val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1, null)
    //val ejemploTres = Suma(1,null)
    println(ejemploUno.sumar())
    println(Suma.historialSumas)
    println(ejemploDos.sumar())
    println(Suma.historialSumas)
    println(ejemploTres.sumar())
    println(Suma.historialSumas)



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


//CLASES

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int,
    ){
        numeroUno=uno
        numeroDos=dos
        println("Inicializar")
    }
}


//instancia numeroUno
//instancia numeroDos
abstract class  Numeros(// constructor Primario
    protected  var numeroUno: Int,
    protected var numeroDos: Int
){
    init {
        println("Inicializar")
    }
}


//instancia numeroUno
//instancia numeroDos

class Suma(
    uno: Int, //parámetro requerido
    dos: Int, //parámetro requerido
):Numeros(//constructor papá(Super)
  uno,
    dos,
){
    init {
        this.numeroUno
        this.numeroDos
         //x -> this.uno -> NO EXISTEN
        //x -> this.dos -> NO EXISTEN
    }
    constructor(//Segundo Constructor
        uno: Int?, //parámetros
        dos: Int //parámetros
    ): this(//llamada constructor primario
        if (uno==null) 0 else uno,
        dos
    )

    constructor(//Tercer Constructor
        uno: Int, //parámetros
        dos: Int? //parámetros
    ): this(//llamada constructor primario
        uno,
        if (dos==null) 0 else dos
    )

    //public fun sumar():Int{
    fun sumar():Int{
        // val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos
        agregarHistoriral(total)
        return total
    }

    //SINGLETON
    companion object{
        val historialSumas= arrayListOf<Int>()
        fun agregarHistoriral(valorNuevaSuma: Int){
            //this.historialSumas.add(valorNuevaSuma)
            historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }

}

