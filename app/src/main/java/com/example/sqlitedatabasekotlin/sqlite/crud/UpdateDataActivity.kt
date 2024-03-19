package com.example.sqlitedatabasekotlin.sqlite.crud


import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlitedatabasekotlin.R
import com.example.sqlitedatabasekotlin.sqlite.databasehelpter.MyDatabaseHelper
import com.google.android.material.textfield.TextInputEditText


class UpdateDataActivity : AppCompatActivity() {
    lateinit var intentIdFromAdapter: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //function call
        getExistingData()

        //update button onClick
        findViewById<Button>(R.id.updateButton).setOnClickListener {
            intentIdFromAdapter = getIntent().getStringExtra("id") as String
            val updatedTitle = findViewById<TextInputEditText>(R.id.updatetitleCreateActTxtInEdTxt).text.toString()
            val updatedAuthor = findViewById<TextInputEditText>(R.id.updateauthorCreateActTxtInEdTxt).text.toString()
            val updatedPages = findViewById<TextInputEditText>(R.id.updatepageCreateActTxtInEdTxt).text.toString()

            val myDatabaseHelper = MyDatabaseHelper(this)
            //calling ypdate method using instance of MydatabaseHelper class
            myDatabaseHelper.updateData(intentIdFromAdapter, updatedTitle, updatedAuthor, updatedPages)
            finish()
        }
    }

    //function outside onCreate getting existing data using intent coming from CustomAdapter
    fun getExistingData() {
        intentIdFromAdapter = getIntent().getStringExtra("id") as String
        val intentTitleFromAdapter = getIntent().getStringExtra("title")
        val intentAuthorFromAdapter = getIntent().getStringExtra("author")
        val intentPagesFromAdapter: String = getIntent().getStringExtra("pages") as String
        findViewById<TextInputEditText>(R.id.updatetitleCreateActTxtInEdTxt).setText(intentTitleFromAdapter)
        findViewById<TextInputEditText>(R.id.updateauthorCreateActTxtInEdTxt).setText(intentAuthorFromAdapter)
        findViewById<TextInputEditText>(R.id.updatepageCreateActTxtInEdTxt).setText(intentPagesFromAdapter)
    }
}