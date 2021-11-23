package com.example.tareasrensosoriano20180498

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tarea::class],version = 1)
abstract class appDatabase: RoomDatabase() {

    abstract fun tareas(): TareaDao

    companion object{
        @Volatile
        private var INSTANCE: appDatabase? = null

        fun getDatabase(context: Context): appDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance

            }

            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    appDatabase:: class.java,
                    "appDatabase"
                ).build()

                INSTANCE = instance

                return  instance
            }

        }
    }

}