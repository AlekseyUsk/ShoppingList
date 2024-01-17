package com.bignerdranch.android.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.shoppinglist.domain.ShopItem

               /**ВТОРОЙ СПОСОБ ЛУЧШЕ 1го*/
class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}
