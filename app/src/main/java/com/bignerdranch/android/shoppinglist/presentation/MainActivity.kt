package com.bignerdranch.android.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.shopList = it
        }
        initRecyclerView()
        onClick()
    }

    private fun initRecyclerView() {
        val recyclerViewShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        shopListAdapter = ShopListAdapter()
        recyclerViewShopList.adapter = shopListAdapter
        /**после того как создал константу(кооличество пулов где указали)
        в адаптере по увеличеванию пула вихолдеров прописываем тут
        и так как у нас 2 VIewType передаем в параметры*/
        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
    }

    private fun onClick() {
        with(shopListAdapter) {
            //исп переменую куда при помощи лямбды передал fun
            onShopItemClickLongListener = {
                viewModel.changeEnableState(it)
            }
            onShopItemClickListener = {
                Log.d("MainActivity", "ЭКРАН ДЛЯ РЕДАКТИРОВАНИЯ ${it.toString()}")
            }
        }
    }
}