package com.bignerdranch.android.shoppinglist.presentation.recyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoppinglist.R

class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val tvName: TextView = view.findViewById<TextView>(R.id.tv_name)
        val tvCount: TextView = view.findViewById<TextView>(R.id.tv_count)

}