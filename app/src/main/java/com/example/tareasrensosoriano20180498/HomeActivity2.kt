package com.example.tareasrensosoriano20180498

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home2.*

enum class ProviderType{

    BASIC
}


class HomeActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        val bundle:Bundle?=intent.extras
       val email:String?= bundle?.getString("email")
        val provider:String?= bundle?.getString("provider")
        setup(email?:"", provider?:"")
        clickTara()
    }





    private fun setup(email:String,provider:String){

        title ="Inicio"
        emailTextView.text=email
        providerTextView2.text=provider

        signUpButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
        clickTara()
    }

    private fun clickTara(){

        irtarea.setOnClickListener {
            val  ventanaTarea:Intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(ventanaTarea)
        }
    }

}