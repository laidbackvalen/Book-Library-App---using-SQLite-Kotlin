package com.example.sqlitedatabasekotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedatabasekotlin.R
import com.example.sqlitedatabasekotlin.sqlite.crud.UpdateDataActivity

class CustomAdapter(
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
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return book_id.size
    }
}