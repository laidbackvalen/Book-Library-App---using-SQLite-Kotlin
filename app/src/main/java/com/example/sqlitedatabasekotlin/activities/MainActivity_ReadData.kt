package com.example.sqlitedatabasekotlin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.sqlitedatabasekotlin.R
import com.example.sqlitedatabasekotlin.adapter.CustomAdapter
import com.example.sqlitedatabasekotlin.sqlite.crud.CreateDataActivity
import com.example.sqlitedatabasekotlin.sqlite.databasehelpter.MyDatabaseHelper

class MainActivity_ReadData : AppCompatActivity() {
    private lateinit var myDatabaseHelper: MyDatabaseHelper

    private lateinit var book_id: ArrayList<String>
    private lateinit var book_title: ArrayList<String>
    private lateinit var book_author: ArrayList<String>
    private lateinit var book_page: ArrayList<String>

    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<LottieAnimationView>(R.id.addlottieIconMain).setOnClickListener {
            val intent = Intent(applicationContext, CreateDataActivity::class.java)
            startActivity(intent)

        }


        myDatabaseHelper = MyDatabaseHelper(this)
        book_id = ArrayList()
        book_title = ArrayList()
        book_author = ArrayList()
        book_page = ArrayList()

        storeDataInArrays()


        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMainActivity)

        customAdapter = CustomAdapter(this, book_id, book_title, book_author, book_page)
        recyclerView.adapter = customAdapter


        val layoutType = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutType




    }

    private fun storeDataInArrays() {
        val cursor = myDatabaseHelper.readAllData()
        if (cursor != null) {
            if (cursor.count == 0) {
                Toast.makeText(this, "No data Found", Toast.LENGTH_SHORT).show()
            } else {
                while (cursor.moveToNext()) { //moveToNext() method is used to move the cursor to the next row in the result set.
                    //it returns true if the cursor successfully moves to the next row, and false if there are no more rows in the result set.

                    book_id.add(cursor.getString(0))  //This retrieves the value of the first column in the current row of the Cursor as a String.
                    book_title.add(cursor.getString(1))
                    book_author.add(cursor.getString(2))
                    book_page.add(cursor.getString(3))

                    //it's important to note that the column index in the Cursor starts from 0,
                    //and the index corresponds to the order in which the columns were selected in your SQL query.
                    //So, getString(0) retrieves the value of the first column selected in your query, getString(1) retrieves the value of the second column, and so on.

                    //For example, if your SQL query is:
                    //String query = "SELECT name, age FROM my_table";
                    //Cursor cursor = db.rawQuery(query, null);
                    //Then getString(0) would retrieve the value of the (name) column, and getString(1) would retrieve the value of the (age) column for the current row in the Cursor.
                }
            }
        }
    }

}


