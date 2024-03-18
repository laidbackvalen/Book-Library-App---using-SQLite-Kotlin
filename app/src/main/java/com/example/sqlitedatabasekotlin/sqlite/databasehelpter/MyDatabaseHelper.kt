package com.example.sqlitedatabasekotlin.sqlite.databasehelpter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//This java class is representing the SQLite Database Schema

//SQLiteOpenHelper - This class will handle database creation, version management, and provide access to the database.
class MyDatabaseHelper : SQLiteOpenHelper {

    lateinit var context : Context

    companion object {
        val DATABASE_NAME = "BookLibrary.db"
        val DATABASE_VERSION = 1

        val TABLE_NAME = "my_library"
        val COLUMN_ID = "id"
        val COLUMN_TITLE = "book_title"
        val COLUMN_AUTHOR = "book_author"
        val COLUMN_PAGES = "book_pages"
    }

    constructor(context: Context?) : super(context, DATABASE_NAME, null, DATABASE_VERSION) {
        if (context != null) {
            this.context = context
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Create your tables here
        val query =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT, $COLUMN_AUTHOR TEXT, $COLUMN_PAGES INTEGER);"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //Upgrade logic here
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    //Function for Inserting data
    fun addBook(title: String, author: String, page: String) {
        //initializing an instance of SQLiteDatabase named db by accessing the writableDatabase property of the current class (this)
        // this - it is an instance of a class that extends SQLiteOpenHelper.
        val db: SQLiteDatabase = this.writableDatabase
        //writableDatabase: This is a property/method provided by the SQLiteOpenHelper class in Android.
        // It returns an instance of SQLiteDatabase that represents a writable database.
        // If the database does not already exist, this method will create it.
        // If the database exists but is not writable, this method will throw an exception.
        var contentValues = ContentValues()
        //ContentValues is a key-value pair data structure
        //used to store values that can be inserted into a SQLite database using the insert() method of SQLiteDatabase.
        contentValues.put(COLUMN_TITLE, title)
        contentValues.put(COLUMN_AUTHOR, author)
        contentValues.put(COLUMN_PAGES, page)

        var result: Long = db.insert(TABLE_NAME, null, contentValues)

        if (result.toInt() == -1) { // (-1) means fails // is used as a convention to indicate that an operation has failed or an error has occurred
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show()
        }

    }
}