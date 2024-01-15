package com.bignerdranch.android.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoppinglist.R
import com.bignerdranch.android.shoppinglist.domain.ShopItem

class Adapter : RecyclerView.Adapter<Adapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById<TextView>(R.id.tv_name)
        val tvCount: TextView = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_disabled,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(shopItemViewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) {
            "Active"
        } else {
            "Not Active"
        }

        shopItemViewHolder.itemView.setOnLongClickListener {
            true
        }
        if (shopItem.enabled) {
            shopItemViewHolder.tvName.text = "${shopItem.name} $status"
            shopItemViewHolder.tvCount.text = shopItem.count.toString()
            shopItemViewHolder.tvName.setTextColor(
                ContextCompat.getColor(
                    shopItemViewHolder.itemView.context,
                    android.R.color.holo_red_light
                )
            )
        } else {
            shopItemViewHolder.tvName.text = ""
            shopItemViewHolder.tvCount.text = ""
            shopItemViewHolder.tvName.setTextColor(
                ContextCompat.getColor(
                    shopItemViewHolder.itemView.context,
                    android.R.color.white
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }
}