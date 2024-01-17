package com.bignerdranch.android.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.shoppinglist.domain.ShopItem

   /**СОЗДАЕМ КЛАСС DiffCallback ДЛЯ МОНИТОРИНГА ИЗМЕНЕНИЙ ОБЕКТОВ ИЛИ ИХ ЭЛЕМЕНТОВ И ПЕРЕДАЧИ В ADAPTER*/
class ShopListDiffCallback(
    private val oldList: List<ShopItem>,
    private val newList: List<ShopItem>
) : DiffUtil.Callback() {

    /**ПЕРВЫЕ ДВА МЕТОДА ВОЗВРАЩАЮТ РАЗМЕР СПИСКОВ**/
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    /**СРАВНИВАЕТ ОБЬЕКТЫ ЧТОБЫ АДАПТЕР ПОНЯЛ РАБОТАЕТ ОН СО СТАРЫМ ИЛИ УЖЕ НОВЫМ ОБЬЕКТОМ**/
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    /**СРАВНИВАЕТ ПОЛЯ ОБЕКТОВ ЧТОБЫ УЗНАТЬ НУЖНО ЛИ ПЕРЕРИСОВАТЬ КАКОЙ ТО ОТДЕЛЬНЫЙ ЭЛЕМЕНТ**/
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}