class PersonasBean {
    private val Espacios:String ="                                                                   "

    var nombre: String = ""
        get() = field
        set(value) {
            field=value
        }

    var apellido: String = ""
        get() = field
        set(value) {
            field=value
        }

    var edad: Int = 0
        get() = field
        set(value) {
            field=value
        }

    var email: String = ""
        get() = field
        set(value) {
            field=value
        }

    var telefono: String = ""
        get() = field
        set(value) {
            field=value
        }

    override fun toString(): String {
        return nombre + Espacios.substring(0, 15 - nombre.length) +
                apellido + Espacios.substring(0, 15 - apellido.length) +
                edad + Espacios.substring(0, 10 - 2) +
                email + Espacios.substring(0, 40 - email.length) +
                telefono + Espacios.substring(0,20-telefono.length) + Espacios.substring(0, 5)
    }


}
