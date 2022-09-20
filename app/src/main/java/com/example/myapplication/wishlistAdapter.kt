package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.net.URL
import kotlin.coroutines.coroutineContext

class wishlistAdapter(private val context: Context, private val wishlist:MutableList<Wishlist>): RecyclerView.Adapter<wishlistAdapter.ViewHolder>()
{

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnLongClickListener,View.OnClickListener{

        val itemTextView: TextView
        val priceTextView: TextView
        val urlTextView: TextView
        init {
            itemTextView = itemView.findViewById(R.id.item)
            priceTextView = itemView.findViewById(R.id.price)
            urlTextView = itemView.findViewById(R.id.url)

            itemView.setOnLongClickListener(this)
            itemView.setOnClickListener(this)

        }

        override fun onLongClick(view: View): Boolean {
            // Return true to indicate the click was handled
            var pos = adapterPosition
            onLC(pos)
            notifyItemRemoved(pos)
            return true
        }

        override fun onClick(view: View) {
            var pos = adapterPosition
            onNavi(pos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.list_view,parent,false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wishlist[position].item
        val price = wishlist[position].price
        val url = wishlist[position].url

        holder.itemTextView.text = item
        holder.priceTextView.text = price.toString()
        holder.urlTextView.text = url.toString()
    }

    public fun onLC(pos : Int){
        wishlist.removeAt(pos)
    }

    public fun onNavi(pos: Int){

        val item = wishlist[pos]
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url.toString()))
            ContextCompat.startActivity(context, browserIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Invalid URL for " + item.item, Toast.LENGTH_LONG).show()
        }
    }
    override fun getItemCount(): Int {
        return wishlist.size
    }
}