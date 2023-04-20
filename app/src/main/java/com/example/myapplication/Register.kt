package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.dataclasses.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            val user = binding.E1.text.toString()
            val email = binding.E2.text.toString()
            val password = binding.E3.text.toString()

            if(user.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this,Login::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "registo falhado", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show()
            }
        }

    }



}
