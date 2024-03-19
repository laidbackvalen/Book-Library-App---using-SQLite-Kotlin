package com.example.sqlitedatabasekotlin.sqlite.databasehelpter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.sql.Array

//This java class is representing the SQLite Database Schema

//SQLiteOpenHelper - This class will handle database creation, version management, and provide access to the database.
class MyDatabaseHelper : SQLiteOpenHelper {

    lateinit var context: Context

    companion object {  //Static variables
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
    //Traverse means -travel accross or travel through
    //A database cursor is a mechanism that allows you to traverse through the records in a database.
    //It's a database-level object that acts as a pointer to a result set of a query,
    //allowing you to move through the rows, fetch data, and optionally modify or delete it.
    //This allows you to process the data row by row, rather than as a whole set.
    //For example, you can use a cursor to perform logic or calculations on each record,
    //or to call a stored procedure or function for each record.

    //Read //Retrieve Data
    fun readAllData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME";
        val db: SQLiteDatabase =
            this.readableDatabase  // It retrieves all data from a specified table (tableName) and returns a Cursor object representing the result set.
        var cursor: Cursor? = null

        if (db != null) {
            cursor = db.rawQuery(
                query,
                null
            )  //This method is called on an instance of SQLiteDatabase (db in this case) to execute a raw SQL query.
            //The first parameter query is a string containing the SQL query to be executed.
            //The second parameter is an array of strings representing the query arguments,
            //which can be null if the SQL query doesn't have any arguments.

            //rawQuery() - This method allows you to execute any SQL query directly without the need for predefined selection, update, deletion, or insertion methods.
        }
        return cursor
    }



    //Update Data
    fun updateData(row_id:String, title: String, author: String, page: String) {

        val db: SQLiteDatabase = this.writableDatabase
        var contentValues: ContentValues = ContentValues()
        contentValues.put(COLUMN_TITLE, title)  // Replace "column_name" with the name of the column you want to update
        contentValues.put(COLUMN_AUTHOR, author)
        contentValues.put(COLUMN_PAGES, page)

        val whereClause = "id=?" // Specify the condition for updating
        val whereArgs  = arrayOf(row_id.toString()) // Provide the value for the placeholder in the WHERE clause

        val result = db.update(TABLE_NAME, contentValues, whereClause, whereArgs)

        if (result.toInt() == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()

        }
    }
}