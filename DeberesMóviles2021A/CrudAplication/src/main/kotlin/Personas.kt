import java.io.*
import java.util.*
import java.util.regex.Pattern
import javax.swing.JOptionPane

class Personas {
    val objPersona = PersonasBean()
    var iSiga = 0
    val strEspacio: String = "                          "
    var strAux: String = ""
    var strTextoPantalla: String = ""
    var bln = false

    fun Personas() {
        var content: String = "Nombre" + strEspacio.substring(0, 8) + "Apellido" + strEspacio.substring(0, 8) +
                "Edad" + strEspacio.substring(0, 8) + "Correo Electrónico" + strEspacio.substring(0, 20) +
                "Teléfono"
        println(content)


        while (!bln) {
            strAux = registrarDatosPersona(
                strTextoPantalla,
                "Ingrese su Nombre",
                "^[a-zñA-ZÑ]+$",
                "Su Nombre tiene caracteres numéricos o especiales",
                15,
                "El tamaño máximo debe ser de 15 caracteres"
            )
            objPersona.nombre = strAux


            strAux = registrarDatosPersona(
                strTextoPantalla,
                "Ingrese su Apellido",
                "^[a-zñA-ZÑ]+$",
                "Su Apellido tiene caracteres numéricos o especiales",
                15,
                "El tamaño máximo debe ser de 15 caracteres"
            )
            objPersona.apellido = strAux


            strAux = registrarDatosPersona(
                strTextoPantalla,
                "Ingrese su Edad",
                "^([0-9])*",
                "La edad ingresada es inválida",
                2,
                "Tiene que ser mayor de Edad"
            )
            objPersona.edad = strAux.toInt()


            strAux = registrarDatosPersona(
                strTextoPantalla,
                "Ingrese su Correo Electrónico",
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
                "El Correo Electrónico ingresado es inválido",
                50,
                "Ha exedido el tamaño máximo de caracteres"
            )
            objPersona.email = strAux


            strAux = registrarDatosPersona(
                strTextoPantalla,
                "Ingrese su Teléfono Celular",
                "^([0-9])*",
                "El teléfono celular ingresado es inválido",
                10,
                "El teléfono celular debe ser de 10 caracteres"
            )
            objPersona.telefono = strAux


            objPersona.nombre
            objPersona.apellido
            objPersona.edad
            objPersona.email
            objPersona.telefono
            print("\n")
            print(objPersona)
            ingresarPersonas(objPersona.toString(), "personas.txt")


            iSiga = JOptionPane.showConfirmDialog(null, "Confirme si desea ingresar nuevas personas")
            if (iSiga != 0) {
                println("\n\nPersonas Ingresadas Exitosamente")
                bln = true
            }
        }
    }

    private fun registrarDatosPersona(
        strTextoPantalla: String?,
        s: String?,
        s2: String?,
        s3: String?,
        i2: Int,
        s4: String?
    ): String {
        var strTextoPantalla = strTextoPantalla
        var strAux: String
        var blnchitory: Boolean
        do {
            strAux = JOptionPane.showInputDialog(s, strTextoPantalla)
            blnchitory = true
            if (!Pattern.matches(s2, strAux)) {
                JOptionPane.showMessageDialog(null, s3, "Mensaje de error al digitar", JOptionPane.ERROR_MESSAGE)
                blnchitory = false
                strTextoPantalla = strAux
            } else if (strAux.length > i2) {
                JOptionPane.showMessageDialog(null, s4, "Mensaje de error al digitar", JOptionPane.ERROR_MESSAGE)
                blnchitory = false
                strTextoPantalla = strAux
            }
        } while (blnchitory == false)
        return strAux
    }


/*fun crearArchivoPersonas() {
        val fileName = "personas.txt"
        var fileObject = File(fileName)
        val strEspacio: String = "                             "
        var content:String = "Nombre" + strEspacio.substring(0, 8) + "Apellido" + strEspacio.substring(0, 8)+
                "Edad" + strEspacio.substring(0, 8) + "Correo Electrónico" + strEspacio.substring(0, 20)+
                "Teléfono"
        val isNewFileCreated: Boolean = fileObject.createNewFile()
        if (isNewFileCreated) {
            println("$fileName is created successfully.")
            escribirArchivo(content,"personas.txt")
        } else {
            println("$El archivo ya existe.")
        }
    }*/

    fun leerPersonas() {
        val file = File("personas.txt")
        println("")
        file.forEachLine { println(it) }
    }

    fun ingresarPersonas(
        str: String,
        nombreArchivo: String
    ) {
        try {
            FileWriter(nombreArchivo, true).use { fw ->
                BufferedWriter(fw).use { bw ->
                    PrintWriter(bw).use { out ->
                        out.print("\n" + str)
                    }
                }
            }
        } catch (e: IOException) {
            println("Existio un error al escribir en el Archivo")
        }
    }

    private fun leerConsola(): String {
        val teclado = Scanner(System.`in`)
        val str = teclado.nextLine()
        return str.uppercase()
    }

    fun actualizarPersonas() {
        var oldvalue = ""
        var actualizado = ""
        var id = ""
        var opcion = ""
        recorrerLineasArchivo()
        id = registrarDatosPersona(
            strTextoPantalla,
            "Ingrese el ID de la Persona que desea Actualizar",
            "^([0-9])*",
            "EL valor ingresado es inválido",
            2,
            "EL valor ingresado es inválido"
        )
        recorrerArchivo(id)
        while (true) {
           print( "Ingrese 1 Para Actualizar Correo Electrónico \n" +
                   "2 para actualizar Teléfono \n" +
                   "3 Para Salir  \n" +
                   "Opcion: "
            )
            when (leerConsola()) {
                ("1") -> {
                    recorrerArchivo(id)
                    oldvalue = registrarDatosPersona(
                        strTextoPantalla,
                        "Ingrese el Correo Electrónico Actual",
                        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
                        "El Correo Electrónico ingresado es inválido",
                        50,
                        "Ha exedido el tamaño máximo de caracteres"
                    )

                    actualizado = registrarDatosPersona(
                        strTextoPantalla,
                        "Ingrese su nuevo Correo Electrónico",
                        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
                        "El Correo Electrónico ingresado es inválido",
                        50,
                        "Ha exedido el tamaño máximo de caracteres"
                    )
                    try {
                        val inputStream: File = File("personas.txt")
                        var text = inputStream.bufferedReader().use { it.readText() }
                        text = text.replace(oldvalue.toRegex(), actualizado)
                        File("personas.txt").writeText(text)
                        println("")
                        println("Datos de Persona Actualizado")
                        leerPersonas()
                        break
                    } catch (e: Exception) {
                        println("Existió un error al actualizar el Archivo")
                    }
                }

                ("2") -> {
                    recorrerArchivo(id)
                    oldvalue = registrarDatosPersona(
                        strTextoPantalla,
                        "Ingrese el Teléfono Celular Actual",
                        "^([0-9])*",
                        "El teléfono celular ingresado es inválido",
                        10,
                        "El teléfono celular debe ser de 10 caracteres"
                    )
                    actualizado = registrarDatosPersona(
                        strTextoPantalla,
                        "Ingrese su Nuevo Teléfono Celular",
                        "^([0-9])*",
                        "El teléfono celular ingresado es inválido",
                        10,
                        "El teléfono celular debe ser de 10 caracteres"
                    )
                    try {
                        val inputStream: File = File("personas.txt")
                        var text = inputStream.bufferedReader().use { it.readText() }
                        text = text.replace(oldvalue.toRegex(), actualizado)
                        File("personas.txt").writeText(text)
                        println("")
                        println("Datos de Persona Actualizado")
                        leerPersonas()
                        break
                    } catch (e: Exception) {
                        println("Existió un error al actualizar el Archivo")
                    }
                }
                ("3") -> break
                else -> println("Opcion no valida")
            }
        }
    }

    fun recorrerLineasArchivo() {
        var lineNumber = 0
        println("")
        File("personas.txt").forEachLine {
            ++lineNumber
            if (lineNumber == 1) {
                lineNumber == null
            } else {
                println("ID:$lineNumber: $it")
            }
        }
    }


    fun recorrerArchivo(
        startLine:String
    ) {
        var lineNumber = 0
        var id:Int
        println("")
        id=startLine.toInt()
        File("personas.txt").forEachLine {
            ++lineNumber
            if (lineNumber == id) {
                println("ID:$lineNumber: $it")
            }
        }
    }


    fun eliminarPersonas() {
        val fileName="personas.txt"
        val numLines= 1
        val startLine: String
        recorrerLineasArchivo()
        startLine = registrarDatosPersona(
            strTextoPantalla,
            "Ingrese el ID de la Persona que desea Eliminar",
            "^([0-9])*",
            "EL valor ingresado es inválido",
            2,
            "EL valor ingresado es inválido"
        )
        val id = startLine.toInt()
        require(!fileName.isEmpty() && id >= 1 && numLines >= 1)
        val f = File(fileName)
        if (!f.exists()) {
            println("$fileName no existe")
            return
        }
        var lines = f.readLines()
        val size = lines.size
        if (id > size) {
            return
        }
        var n = numLines
        if (id + numLines - 1 > size) {
            n = size - id + 1
        }
        lines = lines.take(id - 1) + lines.drop(id + n - 1)
        val text = lines.joinToString(System.lineSeparator())
        f.writeText(text)
        println("Persona eliminada exitosamente")
        leerPersonas()
    }



}


