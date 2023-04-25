package br.com.exp.einkaufen.datasource

import android.util.Log
import br.com.exp.einkaufen.model.Item
import kotlin.math.log

object ItemDataSource {

    private val listOfItems = arrayListOf<Item>()

    fun getList() = listOfItems

    fun insertItem(item: Item){

        if ( !listOfItems.contains(item)) {
            listOfItems.add(item)
            Log.i("TAG", "insertItem: $listOfItems")
        }

    }

}