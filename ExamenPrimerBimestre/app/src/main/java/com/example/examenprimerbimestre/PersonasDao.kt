package com.example.examenprimerbimestre

import androidx.lifecycle.LiveData
import androidx.room.*


@Entity(tableName = "Personas_table")
data class PersonaEntity(
    @PrimaryKey(autoGenerate = true)
    var id_Persona: Int = 0,

    @ColumnInfo(name = "Nombre_persona")
    val Nombre_persona: String = "",

    @ColumnInfo(name = "Apellido_persona")
    var Apellido_persona: String = "",

    @ColumnInfo(name = "Edad_persona")
    var Edad_persona: Int = 0,

    @ColumnInfo(name = "Email_persona")
    var Email_persona: String = "",

    @ColumnInfo(name = "Telefono_persona")
    var Telefono_persona: String = "",
    
)

@Dao
interface PersonaDao {

    @Insert
    fun insert_persona(persona: PersonaEntity)

    @Update
    fun update_persona(persona: PersonaEntity)

    @Query("SELECT * from Personas_table WHERE id_Persona = :key")
    fun get_id_persona(key: Int): LiveData<PersonaEntity>?

    @Query("SELECT * from Personas_table WHERE Nombre_persona = :nombre AND Apellido_persona = :apellido")
    fun get_nombre_persona(nombre: String, apellido: String): LiveData<PersonaEntity>?

    @Query("DELETE FROM Personas_table WHERE Nombre_persona = :nombre AND Apellido_persona = :apellido")
    fun delete_persona(nombre: String, apellido: String)

    @Query("DELETE FROM Personas_table")
    fun clear_persona()

    @Query("SELECT * FROM Personas_table ORDER BY id_Persona DESC")
    fun AllPersonas(): LiveData<List<PersonaEntity>>
}
