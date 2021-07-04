import java.io.*
import java.util.*
import java.util.regex.Pattern
import javax.swing.JOptionPane


class Productos {
    val objProduct = ProductosBean()
    val file = File("productos.txt")
    val strEspacio: String = "                             "
    var strAux: String = ""
    var strTextoPantalla: String = ""
    fun Productos() {
        var iSiga = 0
        var bln = false

        var content: String = "NombreProducto" + strEspacio.substring(0, 8) + "Precio" + strEspacio.substring(0, 8) +
                "Disponibilidad" + strEspacio.substring(0, 15) + "Fecha de Ingreso" +
                strEspacio.substring(0, 28) + "Cantidad"
        println("")
        println(content)


        while (!bln) {
            strAux = registrarDatosProducto(
                strTextoPantalla,
                "Ingrese el Nombre del Producto que desea",
                "^[a-zñA-ZÑ]+$",
                "Su Nombre del Producto tiene caracteres numéricos o especiales",
                15,
                "Ha exedido el tamaño máximo de caracteres"
            )
            objProduct.nombreProducto = strAux


            strAux = registrarDatosProducto(
                strTextoPantalla,
                "Ingrese el precio de su Producto",
                "^(?:\\+|-)?\\d+\\.\\d*$",
                "El precio de su Producto contine letras o caracteres especiales",
                5,
                "El valor debe ser de 5 caracteres"
            )
            objProduct.precio = strAux.toDouble()

            objProduct.disponibilidad = true

            objProduct.fechaIngreso = Date()

            strAux = registrarDatosProducto(
                strTextoPantalla,
                "Ingrese cuantos artículos desea",
                "^([0-9])*",
                "La cantidad ingresada es inválida",
                3,
                "La Cantidad ingresada no es válida "
            )
            objProduct.cantidad = strAux.toInt()

            objProduct.nombreProducto
            objProduct.precio
            objProduct.disponibilidad
            objProduct.fechaIngreso
            objProduct.cantidad
            print("\n")
            print(objProduct)
            ingresarProductos(objProduct.toString(), "productos.txt")

            iSiga = JOptionPane.showConfirmDialog(null, "Confirme si desea ingresar nuevos productos")
            if (iSiga != 0) {
                println("\n\nProductos Ingresados Exitosamente")
                bln = true
            }

        }
    }

    private fun registrarDatosProducto(
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


    /*fun crearArchivoProductos() {
        val fileName = "productos.txt"
        var fileObject = File(fileName)
        val strEspacio: String = "                             "
        var content:String ="NombreProducto" + strEspacio.substring(0, 8) + "Precio" + strEspacio.substring(0, 8)+
                "Disponibilidad" + strEspacio.substring(0, 15) + "Fecha de Ingreso" +
                strEspacio.substring(0, 28) + "Cantidad"
        val isNewFileCreated: Boolean = fileObject.createNewFile()
        if (isNewFileCreated) {
            println("$fileName is created successfully.")
            ingresarProductos(content,"productos.txt")
        } else {
            println("$fileName already exists.")
        }
    }*/

    fun ingresarProductos(
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
            println("Existió un error al escribir en el Archivo")
        }
    }


    fun leerProductos() {
        println("")
        file.forEachLine { println(it) }
    }

    private fun leerConsola(): String {
        val teclado = Scanner(System.`in`)
        val str = teclado.nextLine()
        return str.uppercase()
    }

    fun actualizarProductos() {
        var oldvalue = ""
        var actualizado = ""
        var id = ""
        var opcion = ""
        recorrerLineasArchivo()
        id = registrarDatosProducto(
            strTextoPantalla,
            "Ingrese el ID del Producto que desea Actualizar",
            "^([0-9])*",
            "EL valor ingresado es inválido",
            2,
            "EL valor ingresado es inválido"
        )
        recorrerArchivo(id)
        while (true) {
            print(
                "Ingrese 1 Para Actualizar Precio \n" +
                        "2 para actualizar Cantidad \n" +
                        "3 Para Salir  \n" +
                        "Opcion: "
            )
            when (leerConsola()) {
                ("1") -> {
                    recorrerArchivo(id)
                    oldvalue = registrarDatosProducto(
                        strTextoPantalla,
                        "Ingrese el Precio Actual del Producto",
                        "^(?:\\+|-)?\\d+\\.\\d*$",
                        "El precio de su Producto contine letras o caracteres especiales",
                        5,
                        "El valor debe ser de 5 caracteres"
                    )
                    actualizado = registrarDatosProducto(
                        strTextoPantalla,
                        "Ingrese el Nuevo Precio del Producto",
                        "^(?:\\+|-)?\\d+\\.\\d*$",
                        "El precio de su Producto contine letras o caracteres especiales",
                        5,
                        "El valor debe ser de 5 caracteres"
                    )
                    try {
                        val inputStream: File = File("productos.txt")
                        var text = inputStream.bufferedReader().use { it.readText() }
                        text = text.replace(oldvalue.toRegex(), actualizado)
                        File("productos.txt").writeText(text)
                        println("")
                        println("Datos de Producto Actualizado")
                        leerProductos()
                        break
                    } catch (e: Exception) {
                        println("Existió un error al actualizar el Archivo")
                    }
                }
                ("2") -> {
                    recorrerArchivo(id)
                    oldvalue = registrarDatosProducto(
                        strTextoPantalla,
                        "Ingrese la Cantidad de Artículos Actuales",
                        "^([0-9])*",
                        "La cantidad ingresada es inválida",
                        3,
                        "La Cantidad ingresada no es válida "
                    )
                    actualizado = registrarDatosProducto(
                        strTextoPantalla,
                        "Ingrese la Nueva Cantidad de Artículos",
                        "^([0-9])*",
                        "La cantidad ingresada es inválida",
                        3,
                        "La Cantidad ingresada no es válida "
                    )
                    try {
                        val inputStream: File = File("productos.txt")
                        var text = inputStream.bufferedReader().use { it.readText() }
                        text = text.replace(oldvalue.toRegex(), actualizado)
                        File("productos.txt").writeText(text)
                        println("")
                        println("Datos de Producto Actualizado")
                        leerProductos()
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

    fun recorrerArchivo(
        startLine:String
    ) {
        var lineNumber = 0
        var id:Int
        println("")
        id=startLine.toInt()
        File("productos.txt").forEachLine {
            ++lineNumber
            if (lineNumber == id) {
                println("ID:$lineNumber: $it")
            }
        }
    }

    fun recorrerLineasArchivo() {
        var lineNumber = 0
        println("")
        File("productos.txt").forEachLine {
            ++lineNumber
            if (lineNumber == 1) {
                lineNumber == null
            } else {
                println("ID: $lineNumber: $it")
            }
        }
    }



    fun eliminarProductos() {
        val fileName="productos.txt"
        val numLines= 1
        val startLine: String
        recorrerLineasArchivo()
        startLine = registrarDatosProducto(
            strTextoPantalla,
            "Ingrese el ID del Artículo que desea Eliminar",
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
        println("Producto eliminado exitosamente")
        leerProductos()
    }







}