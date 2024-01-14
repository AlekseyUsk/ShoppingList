package com.bignerdranch.android.shoppinglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.shoppinglist.R
import com.bignerdranch.android.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var linearLayoutShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutShopList = findViewById(R.id.ll_shop_list)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this)
        {
            showLinearLayoutList(it)
        }

    }

    private fun showLinearLayoutList(list: List<ShopItem>) {
        linearLayoutShopList.removeAllViews()
        //помещаю cardView в view макета
        for (item in list) {
            val layoutId = if (item.enabled) {
                R.layout.item_shop_enabbled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId, linearLayoutShopList, false)
            //нахожу в макете вьюшки
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = item.name
            tvCount.text = item.count.toString()
            view.setOnLongClickListener{
                viewModel.changeEnableState(item)
                true
            }
            //добавляю этот готовый view в макет
            linearLayoutShopList.addView(view)
        }
    }
}