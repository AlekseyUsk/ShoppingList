package com.bignerdranch.android.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.shoppinglist.R
import com.bignerdranch.android.shoppinglist.presentation.recyclerView.ShopListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        initRecyclerView()
        onClick()
    }

    private fun initRecyclerView() {
        val recyclerViewShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        shopListAdapter = ShopListAdapter()
        recyclerViewShopList.adapter = shopListAdapter

        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        setupSwipeListener(recyclerViewShopList)
    }

    /**СВАЙП + УДАЛЕНИЕ ЭЛЕМЕНТА
     * 1 - нужен симплкалбек из класса айтем тач хелпер
     * передаем в конструктор направление перемещение олбьекта и свайпа
     * 2 - переопределяем 2 шт метода
     * 3 - в методе onSwiped получаю элемент по которому произошло нажатие и удаляю его
     * 4 - создаем каллбек itemTouchHelper и в конструктор передаем callback
     * 5 - устанавливаем itemTouchHelper.attachToRecyclerView(rvShopList)*/
    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            /**этот метод неиспользую -> вернул просто return false */
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            /**в методе onSwiped получаю элемент по которому произошло нажатие и удаляю его*/
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun onClick() {
        with(shopListAdapter) {
            onShopItemClickLongListener = {
                viewModel.changeEnableState(it)
            }
            onShopItemClickListener = {
                Log.d("MainActivity", "ЭКРАН ДЛЯ РЕДАКТИРОВАНИЯ ${it.toString()}")
            }
        }
    }
}