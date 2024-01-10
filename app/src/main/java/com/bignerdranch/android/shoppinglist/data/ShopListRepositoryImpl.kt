package com.bignerdranch.android.shoppinglist.data

import com.bignerdranch.android.shoppinglist.domain.ShopItem
import com.bignerdranch.android.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    //заглушка в переменную записал данные(тут использовать базу данных)
    private val shopList = mutableListOf<ShopItem>()

    //переменная для авто добавления id элементу
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
    //сделал проверку иначе вылетит ошибка
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId
            autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        shopList.add(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Элемент с идентификатором $shopItemId не найден")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}