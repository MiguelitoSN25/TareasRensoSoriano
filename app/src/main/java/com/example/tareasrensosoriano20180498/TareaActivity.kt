package com.example.tareasrensosoriano20180498

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_tarea.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TareaActivity : AppCompatActivity() {


    private lateinit var database: appDatabase
    private lateinit var tarea: Tarea
    private lateinit var tareaLiveData: LiveData<Tarea>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarea)

        database = appDatabase.getDatabase(this)

         val idTarea = intent.getIntExtra("id",0)

        tareaLiveData = database.tareas().get(idTarea)
        tareaLiveData.observe(this, Observer {
            tarea = it

            nombre_tarea.text = tarea.nombre
            estado_tarea.text = tarea.estado
            descripcion_tarea.text = tarea.descripcion

        })




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.tarea_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.edit_Item -> {

                val intent = Intent(this, NuevaTareaActivity:: class.java)
                intent.putExtra("tarea", tarea)
                startActivity(intent)
            }

            R.id.delete_Item -> {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Eliminar")
                builder.setMessage("Deseas Eliminar la Tarea?")


                tareaLiveData.removeObservers(this)
                CoroutineScope(Dispatchers.IO).launch {
                    database.tareas().delete(tarea)
                    this@TareaActivity.finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}