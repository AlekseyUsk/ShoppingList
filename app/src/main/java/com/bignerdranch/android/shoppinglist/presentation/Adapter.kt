package com.bignerdranch.android.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoppinglist.R
import com.bignerdranch.android.shoppinglist.domain.ShopItem

class Adapter : RecyclerView.Adapter<Adapter.ShopItemViewHolder>() {

    private val list = listOf<ShopItem>()

    class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById<TextView>(R.id.tv_name)
        val tvCount: TextView = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(shopItemViewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = list[position]
        shopItemViewHolder.tvName.text = shopItem.name
        shopItemViewHolder.tvCount.text = shopItem.count.toString()
        shopItemViewHolder.itemView.setOnLongClickListener{
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}