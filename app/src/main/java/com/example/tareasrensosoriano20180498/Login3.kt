package com.example.tareasrensosoriano20180498

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login3.*

class Login3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login3)
        setup()

    }

    private fun setup(){

        title = "Autenticacion"
        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditTextT.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditTextT.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                    }else{

                        showAlert()
                    }
                }




            }
        }

        LoginButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditTextT.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditTextT.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                    }else{

                        showAlert()
                    }
                }




            }
        }
    }

    private fun showAlert(){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha Producido un Error Autenticando al Usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }

    private  fun showHome(email:String,provider:ProviderType){

        val homeIntent = Intent(this,HomeActivity2::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }

        startActivity(homeIntent)
    }
}