package com.example.examenprimerbimestre
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class Database {
    @Database(entities = [PersonaEntity::class, ProductoEntity::class], version = 6, exportSchema = false)
    abstract class PersonasDatabase : RoomDatabase()  {

        abstract val personaDao: PersonaDao
        abstract val productoDao: ProductoDao

        companion object {

            @Volatile
            private var INSTANCE: PersonasDatabase? = null

            fun getInstance(context: Context): PersonasDatabase {
                synchronized(this) {
                    var instance = INSTANCE

                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            PersonasDatabase::class.java,
                            "sleep_history_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
    }
}