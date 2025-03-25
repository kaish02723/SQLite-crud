package com.example.crudd

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class update_user : AppCompatActivity() {
    private var SQLiteHelperData = SQLiteHelperData(this)
    var uId: Int = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_user)

        uId = intent.getIntExtra("id", -1)
        if (uId == -1) {
            finish()
            return
        }


        val nameEditTextUpdate = findViewById<EditText>(R.id.editTextTextNameUpdate)
        val emailEditTextUpdate = findViewById<EditText>(R.id.editTextText2EmailUpdate)
        val buttonClickDataUpdate = findViewById<Button>(R.id.updateDataButton)

        buttonClickDataUpdate.setOnClickListener {
            val name = nameEditTextUpdate.text.toString()
            val email = emailEditTextUpdate.text.toString()
            val model = userMOdelClass(uId, name, email)

            SQLiteHelperData.updateUserItem(model)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}