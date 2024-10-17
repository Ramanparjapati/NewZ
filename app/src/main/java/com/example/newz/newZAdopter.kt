package com.example.newz

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class NewZAdopter(private val listener: NewItemClicked): RecyclerView.Adapter<NewZViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewZViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newz_items, parent, false)
        val viewHolder = NewZViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewZViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }


    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

}

class NewZViewHolder(itemView: View) : ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val author: TextView = itemView.findViewById(R.id.author)
    val image: ImageView = itemView.findViewById(R.id.image)
}

interface NewItemClicked{
    fun onItemClicked(item: News)
}