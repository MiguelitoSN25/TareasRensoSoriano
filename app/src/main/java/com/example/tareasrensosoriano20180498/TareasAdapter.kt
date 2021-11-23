package com.example.tareasrensosoriano20180498

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_tareas.view.*

class TareasAdapter(private val mContext: Context, private val listaTareas:List<Tarea>): ArrayAdapter<Tarea>(mContext, 0, listaTareas) {

    //aqui estoy instancaindo el layout
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_tareas, parent,false)

      //informacion de la lista de tareas:

        val tarea = listaTareas[position]

        layout.nombre.text = tarea.nombre
        layout.estado_tarea.text = tarea.estado
        layout.imageView.setImageResource(tarea.imagen)


        return layout
    }
}