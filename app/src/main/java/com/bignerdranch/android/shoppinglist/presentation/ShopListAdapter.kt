package com.bignerdranch.android.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoppinglist.R
import com.bignerdranch.android.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    private var count = 0

    var onShopItemClickListener: ((ShopItem) -> Unit)? = null //в переменную поместил fun (лямбда)

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById<TextView>(R.id.tv_name)
        val tvCount: TextView = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun onBindViewHolder(shopItemViewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        with(shopItemViewHolder) {
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            itemView.setOnLongClickListener {
                onShopItemClickListener?.invoke(shopItem) //вызвал эту функцию при помощи
                // этой переменной - исп invoke так как fun может быть null
            true
            }
        }
    }

    interface OnShopItemClickListener {
        fun onShopItemClick(shopItem: ShopItem)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabbled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> {
                throw RuntimeException("Unknown view type: $viewType")
            }
        }
        Log.d("@@@", "onCreateViewHolder ${++count}")
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    /**ЭТОТ МЕТОД ВЫЗЫВАЕТСЯ ТОГДА КОГДА НАШ МАКЕТ ХОТЯТ ПЕРЕИСПОЛЬЗОВАТЬ
    И ТУТ МЫ МОЖЕМ УСТАНОВИТЬ ЗНАЧЕНИЯ ПО УМОЛЧАНИЮ */
    override fun onViewRecycled(shopItemViewHolder: ShopItemViewHolder) {
        super.onViewRecycled(shopItemViewHolder)
        with(shopItemViewHolder) {
            tvName.text = ""
            tvCount.text = ""
            tvName.setTextColor(
                ContextCompat.getColor(
                    shopItemViewHolder.itemView.context,
                    android.R.color.white
                )
            )
        }
    }

    /**ЭТОТ МЕТОД НУЖЕН ДЛЯ ТЕХ СЛУЧАЕВ КОГДА РАЗНЫЕ ОБЬЕКТЫ ТРЕБУЮТ РАЗНЫЕ ЛАЙАУТЫ(МАКЕТЫ)
    нужен для того чтобы возвращать позицию элемента
    нужен для того чтобы использовать нужный макет в зависимости от типа элемента
    в методе onCreateViewHolder*/
    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 10
    }
}
/**есть проблема когда пулл вихолдеров заполнен и в этом пуле нет нужного вихолдера
то создается нужный вихолдер поэтому в логах при скролле появляются опять вихолдеры
вместо к примеру 20 эта проблема решается увеличиванием пулла вихолдеров*/

