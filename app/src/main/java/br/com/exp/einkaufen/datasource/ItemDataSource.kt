package br.com.exp.einkaufen.datasource

import android.util.Log
import br.com.exp.einkaufen.model.Item

object ItemDataSource {

    private val listOfItems = arrayListOf<Item>()
    //private val mapOfItems = listOfItems.map{ it.item to it.id}.toMap()

    fun getList() = listOfItems
    //fun getMap() = mapOfItems

    fun insertItem(item: Item){
        listOfItems.add(item.copy( id = listOfItems.size + 1))
        val mapOfItems = listOfItems.map{ it.item to it.id}.toMap()

        Log.i("TAG", "insertItem: $mapOfItems")
    } //11min55s timepicker

}