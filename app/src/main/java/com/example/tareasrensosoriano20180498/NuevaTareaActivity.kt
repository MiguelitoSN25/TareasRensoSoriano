package com.example.tareasrensosoriano20180498

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nueva_tarea.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevaTareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_tarea)

        var idTarea: Int? = null
        if(intent.hasExtra("tarea")){

            val tarea = intent.extras?.getSerializable("tarea") as Tarea

            nombre_editext.setText(tarea.nombre)
            estado_editext.setText(tarea.estado)
            descripcion_editext.setText(tarea.descripcion)
            idTarea = tarea.idTarea
        }

        val database = appDatabase.getDatabase(this)

        save_btn.setOnClickListener {
            val nombre = nombre_editext.text.toString()
            val estado = estado_editext.text.toString()
            val descripcion = descripcion_editext.text.toString()

            val tarea = Tarea(nombre,estado,descripcion, R.drawable.ic_launcher_background)

            if(idTarea != null){

                CoroutineScope(Dispatchers.IO).launch {
                    tarea.idTarea = idTarea
                    database.tareas().update(tarea)
                    this@NuevaTareaActivity.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    database.tareas().insertAll(tarea)
                    this@NuevaTareaActivity.finish()

                }
            }

           
        }
    }
}