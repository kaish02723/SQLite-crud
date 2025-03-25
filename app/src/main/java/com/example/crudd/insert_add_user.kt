package com.example.crudd

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class insert_add_user : AppCompatActivity() {
    private var SQLiteHelperData=SQLiteHelperData(this)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insert_add_user)





            val nameEditText=findViewById<EditText>(R.id.editTextTextName)
            val emailEditText=findViewById<EditText>(R.id.editTextText2Email)
            val buttonClickInserData=findViewById<Button>(R.id.insertDataButton)

            buttonClickInserData.setOnClickListener {
                val name=nameEditText.text.toString()
                val email=emailEditText.text.toString()



                val model=userMOdelClass(0,name,email)
                SQLiteHelperData.insertDataFunction(model)
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }



    }
}