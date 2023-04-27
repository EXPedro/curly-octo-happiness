package br.com.exp.einkaufen.utils

import br.com.exp.einkaufen.datasource.ItemDataSource
import br.com.exp.einkaufen.model.Item

class Utils {
    companion object {
        const val UTILS = "Utils"
        private lateinit var tempItem: Item

        fun createStringList( textToConvert: String ): List<String> =
            textToConvert.split(" ")

        fun createItem( stringList: List<String> ) =

            stringList.mapIndexed { _, string ->
                tempItem = Item(stringList.size + 1, string)
                ItemDataSource.insertItem(tempItem)
            }
    }
}