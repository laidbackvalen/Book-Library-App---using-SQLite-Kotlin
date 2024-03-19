package com.example.sqlitedatabasekotlin.sqlite.crud


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlitedatabasekotlin.R
import com.google.android.material.textfield.TextInputEditText


class UpdateDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intentTitleFromAdapter = getIntent().getStringExtra("title")
        val intentAuthorFromAdapter = getIntent().getStringExtra("author")
        val intentPagesFromAdapter: String = getIntent().getStringExtra("pages") as String

        var updateTitleTextInputEditText = findViewById<TextInputEditText>(R.id.updatetitleCreateActTxtInEdTxt).setText(intentTitleFromAdapter)
        var upadteAuthorTextInputEditText = findViewById<TextInputEditText>(R.id.updateauthorCreateActTxtInEdTxt).setText(intentAuthorFromAdapter)
        var updatePagesTextInputEditText = findViewById<TextInputEditText>(R.id.updatepageCreateActTxtInEdTxt).setText(intentPagesFromAdapter)


    }
}