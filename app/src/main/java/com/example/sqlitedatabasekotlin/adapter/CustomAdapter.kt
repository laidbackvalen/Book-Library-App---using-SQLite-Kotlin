package com.example.sqlitedatabasekotlin.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedatabasekotlin.R
import com.example.sqlitedatabasekotlin.activities.MainActivity_ReadData
import com.example.sqlitedatabasekotlin.sqlite.crud.UpdateDataActivity
import com.example.sqlitedatabasekotlin.sqlite.databasehelpter.MyDatabaseHelper

class CustomAdapter(
    val activity: Activity,
    val context: Context, val book_id: ArrayList<String>,
    val book_title: ArrayList<String>, val book_author: ArrayList<String>,
    val book_pages: ArrayList<String>
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textbookId = itemView.findViewById<TextView>(R.id.bookIdRawlayout)
        val textbookTitle = itemView.findViewById<TextView>(R.id.bookTitleRawLayout)
        val textbookAuthor = itemView.findViewById<TextView>(R.id.bookAuthorRawLayout)
        val textbookPages = itemView.findViewById<TextView>(R.id.bookPagesRawLayout)
        val view = itemView.findViewById<View>(R.id.viewRawLayout)
        val deleteIcon = itemView.findViewById<View>(R.id.deleteIconRawLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.raw_layout_for_recyclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textbookId.text = book_id.get(position)
        holder.textbookTitle.text = book_title.get(position)
        holder.textbookAuthor.text = book_author.get(position)
        holder.textbookPages.text = book_pages.get(position)

            holder.itemView.setOnClickListener {
                val intent = Intent(context, UpdateDataActivity::class.java)
                intent.putExtra("id", book_id.get(holder.adapterPosition))
                intent.putExtra("title", book_title.get(holder.adapterPosition))
                intent.putExtra("author", book_author.get(holder.adapterPosition))
                intent.putExtra("pages", book_pages.get(holder.adapterPosition))
                activity.startActivityForResult(intent, 1)
            }

        holder.deleteIcon.setOnClickListener {
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Delete ${book_title.get(holder.adapterPosition)}?")
            builder.setMessage("Are you sure you want to delete ${book_title.get(holder.adapterPosition)}?")
            builder.setPositiveButton("Yes") { dialog: DialogInterface?, _: Int ->

                val myDatabaseHelper = MyDatabaseHelper(context)
                myDatabaseHelper.deleteData(book_id.get(holder.adapterPosition))
                notifyItemRemoved(position)
            }

            builder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            builder.create().show()


        }
    }

    override fun getItemCount(): Int {
        return book_id.size
    }
}


