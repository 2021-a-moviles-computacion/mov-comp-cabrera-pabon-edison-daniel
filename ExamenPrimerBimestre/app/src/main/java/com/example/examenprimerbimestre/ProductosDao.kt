package com.example.examenprimerbimestre

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "Productos_table",
    foreignKeys = [ForeignKey(entity = PersonaEntity::class,
        parentColumns = arrayOf("id_Persona"),
        childColumns = arrayOf("id_Producto"),
        onDelete = ForeignKey.CASCADE)]
)
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true)
    var id_Producto: Int = 0,

    @ColumnInfo(name = "id_Persona")
    val id_Persona: Int = 0,

    @ColumnInfo(name = "Nombre_producto")
    val Nombre_producto: String = "",

    @ColumnInfo(name = "Precio_producto")
    var Precio_producto: Double = 0.0,

    @ColumnInfo(name = "Fecha_ingreso_producto")
    var Fecha_ingreso_producto: String = "",

    @ColumnInfo(name = "Disponibilidad_producto")
    var Disponibilidad_producto: Boolean = true,

    @ColumnInfo(name = "Cantidad_producto")
    var Cantidad_producto: Int = 0
) {
}

@Dao
interface ProductoDao {

    @Insert
    fun insert_producto(producto: ProductoEntity)

    @Update
    fun update_producto(producto: ProductoEntity)

    @Query("SELECT * from Productos_table WHERE id_Producto = :key")
    fun get_id_producto(key: Int): LiveData<ProductoEntity>?

    @Query("SELECT * from Productos_table WHERE Nombre_producto = :nombre")
    fun get_nombre_producto(nombre: String): LiveData<ProductoEntity>?

    @Query("DELETE FROM Productos_table WHERE Nombre_producto = :nombre")
    fun delete_producto(nombre: String)

    @Query("DELETE FROM Productos_table")
    fun clear_producto()

    @Query("SELECT * FROM Productos_table WHERE id_Persona= :id ORDER BY id_Producto DESC")
    fun AllProductos(id: Int): LiveData<List<ProductoEntity>>
}