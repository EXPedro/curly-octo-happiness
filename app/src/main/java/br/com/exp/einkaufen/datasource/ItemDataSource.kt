package br.com.exp.einkaufen.datasource

import android.util.Log
import br.com.exp.einkaufen.model.Item
import kotlin.math.log

object ItemDataSource {

    private val listOfItems = arrayListOf<Item>()
    private const val ITEM_DATA_SOURCE = "ItemDataSource"

    fun getList() = listOfItems

    fun insertItem(item: Item){

        if ( !listOfItems.contains( item )) {
            listOfItems.add( item )
        }

    }

    fun updateItem( item: Item){
        findItem( item.item )
        listOfItems.remove( item )
    }

    private fun findItem(itemId: String ): Item? {
        return listOfItems.find { it.item == itemId }
    }

    fun deleteItem(item: Item) {
        listOfItems.remove( item )
    }

}