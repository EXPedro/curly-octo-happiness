package br.com.exp.einkaufen.utils

import br.com.exp.einkaufen.datasource.ItemDataSource
import br.com.exp.einkaufen.data.Item

class Utils {
    companion object {
        const val UTILS = "Utils"
        private lateinit var tempItem: Item

        fun createStringList(textToConvert: String): List<String> =
            textToConvert.split(" ")

        fun createItem(stringList: List<String>): List<Item> {
            var createdItens = arrayListOf<Item>()

            stringList.map { string ->
                tempItem = Item(string)
                ItemDataSource.insertItem(tempItem)
                createdItens.add(tempItem)
            }

            return createdItens
        }
    }
}