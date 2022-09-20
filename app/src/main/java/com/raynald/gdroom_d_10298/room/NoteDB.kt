package com.raynald.gdroom_d_10298.room

import android.content.Context
import android.content.EntityIterator
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1

)
abstract class NoteDB: RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {

        @Volatile private var instance : NoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                val it = null
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java,
                "note12345.db"
            ).build()
    }
}

annotation class Database(val version: Int)
