package com.bignerdranch.android.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.shoppinglist.data.ShopListRepositoryImpl
import com.bignerdranch.android.shoppinglist.domain.DeleteShopItemUseCase
import com.bignerdranch.android.shoppinglist.domain.EditShopItemUseCase
import com.bignerdranch.android.shoppinglist.domain.GetShopListUseCase
import com.bignerdranch.android.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    //TODO пока неправильная реализация так как presentation слой недолжен знать об data слой.
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}