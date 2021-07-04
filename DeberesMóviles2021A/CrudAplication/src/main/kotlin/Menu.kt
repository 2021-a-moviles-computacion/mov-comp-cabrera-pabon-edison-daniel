import java.awt.EventQueue
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class Menu {
    private var frame: JFrame? = null

    fun main() {
        EventQueue.invokeLater {
            try {
                val window = Menu()
                window.initialize()
                window.frame?.isVisible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun initialize() {
        frame = JFrame()
        frame!!.setBounds(500, 500, 450, 300)
        frame!!.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val menuBar = JMenuBar()
        frame!!.jMenuBar = menuBar
        val mnIngress = JMenu("CREAR")
        menuBar.add(mnIngress)
        val mntmNewMenuItem_1 = JMenuItem("PERSONA")
        mntmNewMenuItem_1.addActionListener {
            val objCl = Personas()
            //objCl.crearArchivoPersonas()
            objCl.Personas()
        }
        mnIngress.add(mntmNewMenuItem_1)
        val mntmNewMenuItem_2 = JMenuItem("PRODUCTO")
        mntmNewMenuItem_2.addActionListener {
            val objPr = Productos()
            //objPr.crearArchivoProductos()
            objPr.Productos()
        }
        mnIngress.add(mntmNewMenuItem_2)


        val mnLeer = JMenu("LEER")
        menuBar.add(mnLeer)
        val mntmNewMenuItem_3 = JMenuItem("PERSONAS")
        mntmNewMenuItem_3.addActionListener {
            val objCl = Personas()
            objCl.leerPersonas()
        }
        mnLeer.add(mntmNewMenuItem_3)
        val mntmNewMenuItem_4 = JMenuItem("PRODUCTOS")
        mntmNewMenuItem_4.addActionListener {
            val objPr = Productos()
            objPr.leerProductos()
        }
        mnLeer.add(mntmNewMenuItem_4)


        val mnActualizar = JMenu("ACTUALIZAR")
        menuBar.add(mnActualizar)
        val mntmNewMenuItem_5 = JMenuItem("PERSONA")
        mntmNewMenuItem_5.addActionListener {
            val objCl = Personas()
            objCl.actualizarPersonas()
        }
        mnActualizar.add(mntmNewMenuItem_5)
        val mntmNewMenuItem_6 = JMenuItem("PRODUCTO")
        mntmNewMenuItem_6.addActionListener {
            val objPr = Productos()
            objPr.actualizarProductos()
        }
        mnActualizar.add(mntmNewMenuItem_6)

        val mnDelete = JMenu("ELIMINAR")
        menuBar.add(mnDelete)
        val mntmNewMenuItem_7 = JMenuItem("PERSONA")
        mntmNewMenuItem_7.addActionListener {
            val objCl = Personas()
            objCl.eliminarPersonas()
        }
        mnDelete.add(mntmNewMenuItem_7)
        val mntmNewMenuItem_8 = JMenuItem("PRODUCTO")
        mntmNewMenuItem_8.addActionListener {
            val objPr = Productos()
            objPr.eliminarProductos()
        }
        mnDelete.add(mntmNewMenuItem_8)
    }
}