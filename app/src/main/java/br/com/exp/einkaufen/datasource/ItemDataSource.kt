package br.com.exp.einkaufen.datasource

import br.com.exp.einkaufen.model.Item

object ItemDataSource {

    private val listOfItems = arrayListOf<Item>()
    private const val ITEM_DATA_SOURCE = "ItemDataSource"

    fun getList() = listOfItems

    fun insertItem(item: Item){

        if ( !listOfItems.contains( item )) {
            listOfItems.add( item )
        }

    }

    fun updateItem(item: Item){
        findItem(item)
        listOfItems.remove(item)
    }

    private fun findItem(item: Item): Item? {
        return listOfItems.find { it == item }
    }

    fun deleteItem(item: Item) {
        listOfItems.remove( item )
    }

}