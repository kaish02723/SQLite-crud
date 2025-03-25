package com.example.crudd

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var bottonSheetAlert: BottomSheetDialog
    private var SQLiteHelperData=SQLiteHelperData(this)
    lateinit var listViewAdapterClass: listViewAdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        bottonSheetAlert= BottomSheetDialog(this)
        var flotingActionButton=findViewById<FloatingActionButton>(R.id.floatingActionButton)
        flotingActionButton.setOnClickListener {
            startActivity(Intent(this,insert_add_user::class.java))
            finish()

        }

        var listView=findViewById<ListView>(R.id.listViewItem)
        listViewAdapterClass=listViewAdapterClass(this,SQLiteHelperData.viewData())
        listView.adapter=listViewAdapterClass


    }

}