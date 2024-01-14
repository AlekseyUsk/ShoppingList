package com.bignerdranch.android.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.shoppinglist.domain.ShopItem
import com.bignerdranch.android.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    //сдела что при редактировании элемент оставался на своем месте по id а не уходил в конец списка
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    //переменная для авто добавления id элементу
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name $i",i,true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
    //сделал проверку иначе вылетит ошибка
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId
            autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Элемент с идентификатором $shopItemId не найден")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    private fun updateList(){
        shopListLiveData.value = shopList.toList()
    }
}