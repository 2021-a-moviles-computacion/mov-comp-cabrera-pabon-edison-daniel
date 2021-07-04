import java.util.*

class ProductosBean {
    private val Espacios:String ="                                                                   "
    var nombreProducto: String = ""
        get() = field
        set(value) {
            field=value
        }

    var precio: Double = 0.0
        get() = field
        set(value) {
            field=value
        }

    var disponibilidad: Boolean = null == true
        get() = field
        set(value) {
            field=value
        }

    var cantidad: Int = 0
        get() = field
        set(value) {
            field=value
        }

    var fechaIngreso: Date? = null
        get() = field
        set(value) {
            field=value
        }


    override fun toString(): String {
        return  nombreProducto + Espacios.substring(0, 22 - nombreProducto.length) +
                precio+ Espacios.substring(0, 15) +
                disponibilidad + Espacios.substring(0, 20) +
                fechaIngreso + Espacios.substring(0, 20) +
                cantidad + Espacios.substring(0, 20) + Espacios.substring(0, 5)
    }


}