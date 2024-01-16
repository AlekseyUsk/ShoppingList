package com.bignerdranch.android.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.shopList = it
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = Adapter()
        recyclerViewShopList.adapter = adapter
        /**после того как создал константу(кооличество пулов где указали)
        в адаптере по увеличеванию пула вихолдеров прописываем тут
        и так как у нас 2 VIewType передаем в параметры*/
        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            Adapter.VIEW_TYPE_ENABLED,
            Adapter.MAX_POOL_SIZE
        )
        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            Adapter.VIEW_TYPE_DISABLED,
            Adapter.MAX_POOL_SIZE
        )
    }
}