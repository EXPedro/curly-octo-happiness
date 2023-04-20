package br.com.exp.einkaufen.utils

import android.util.Log

class Utils {
    fun createStringList( textToConvert: String): List<String>{
        val stringList = textToConvert.split(" ")
        Log.i("Utils", "createStringList: $stringList")
        return stringList
    }
}