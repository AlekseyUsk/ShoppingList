package com.bignerdranch.android.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this)
        {
            Log.d("@@@", it.toString())
            if (count == 0){
                count++
                val item = it[0] //it: List<ShopItem>! из листа берем первый элемент и удаляем
                viewModel.changeEnableState(item) //метод удаления
                //TODO способ когда нужно по листу удалить какой то элемент 1 раз при помощи count++
            }
        }
    }
}