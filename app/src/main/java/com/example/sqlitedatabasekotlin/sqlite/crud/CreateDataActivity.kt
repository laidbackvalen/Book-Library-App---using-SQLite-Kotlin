package com.example.sqlitedatabasekotlin.sqlite.crud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlitedatabasekotlin.R
import com.example.sqlitedatabasekotlin.activities.MainActivity_ReadData
import com.example.sqlitedatabasekotlin.sqlite.databasehelpter.MyDatabaseHelper
import com.google.android.material.textfield.TextInputEditText

class CreateDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.createdata_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.addButtonCreateActivity).setOnClickListener {
            val title =
                findViewById<TextInputEditText>(R.id.titleCreateActTxtInEdTxt).text.toString()
            val author =
                findViewById<TextInputEditText>(R.id.authorCreateActTxtInEdTxt).text.toString()
            val pages =
                findViewById<TextInputEditText>(R.id.pageCreateActTxtInEdTxt).text.toString()

            val myDatabaseHelper = MyDatabaseHelper(this)
            myDatabaseHelper.addBook(title, author, pages)

            val intent = Intent(this, MainActivity_ReadData::class.java)
            startActivity(intent)
           finish()

        }
    }

}