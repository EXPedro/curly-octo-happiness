package br.com.exp.einkaufen.datasource

import android.util.Log
import br.com.exp.einkaufen.model.Item
import kotlin.math.log

object ItemDataSource {

    private val listOfItems = arrayListOf<Item>()

    fun getList() = listOfItems

    fun insertItem(item: Item){

        if ( !listOfItems.contains( item )) {
            if ( item.id == 0 ) {
                listOfItems.add( item )
            }else {
                listOfItems.remove( item )
                listOfItems.add( item.copy( id = listOfItems.size + 1) )
            }
        }

    }

    fun findItemById( itemId: Int ): Item? {
        Log.i("findItemById", "$itemId" )
        return listOfItems.find { it.id == itemId }
    }

    fun deleteItem(item: Item) {
        listOfItems.remove( item )
    }

}