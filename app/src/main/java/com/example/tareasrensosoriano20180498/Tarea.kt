package com.example.tareasrensosoriano20180498

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tareas")

class Tarea (val nombre:String,
             val estado:String,
             val descripcion: String,
             val imagen:Int,
             @PrimaryKey(autoGenerate = true)
             var idTarea: Int = 0
): Serializable