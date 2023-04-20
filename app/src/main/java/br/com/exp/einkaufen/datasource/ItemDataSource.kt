package br.com.exp.einkaufen.datasource

import br.com.exp.einkaufen.model.Item

object ItemDataSource {

    private val listOfItems = arrayListOf<Item>()

    fun getList() = listOfItems

    fun insertItem(item: Item){
        listOfItems.add(item.copy( id = listOfItems.size + 1))
    } //11min55s timepicker

}