package com.example.tareasrensosoriano20180498

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var listaTarea = emptyList<Tarea>()

        val database = appDatabase.getDatabase(this)
        database.tareas().getAll().observe(this, Observer {
            listaTarea = it

            val adaptador = TareasAdapter(this,listaTarea)

            lista.adapter = adaptador
        })


        lista.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this, TareaActivity::class.java)
            intent.putExtra("id",listaTarea[position].idTarea)
            startActivity(intent)

        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NuevaTareaActivity::class.java)
            startActivity(intent)
        }
    }
}