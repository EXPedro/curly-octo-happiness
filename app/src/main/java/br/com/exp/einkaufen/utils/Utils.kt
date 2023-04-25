package br.com.exp.einkaufen.utils

import android.util.Log
import br.com.exp.einkaufen.datasource.ItemDataSource
import br.com.exp.einkaufen.model.Item

class Utils {

    companion object {
        const val UTILS = "Utils"
        private lateinit var tempItem: Item

        fun createStringList( textToConvert: String): List<String>{

            val stringList = textToConvert.split(" ")
            Log.i(UTILS, "createStringList: $stringList")
            return stringList

        }

        fun createItem(stringList: List<String>){

            stringList.mapIndexed { index, string ->
                tempItem = Item(index, string)
                ItemDataSource.insertItem(tempItem)
            }

            Log.i(UTILS, "createItem: ${ItemDataSource.getList()} ")

        }

    }
}