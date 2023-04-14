package br.com.exp.einkaufen.controller

import android.util.Log
import br.com.exp.einkaufen.view.AddItem
import com.google.android.material.textfield.TextInputLayout

class AddItemController() {

    fun capturaString(inputText: TextInputLayout): String{
        Log.i(ADD_ITEM_CONTROLLER, "$inputText")
        return "Hello darkness. It's ${inputText.editText?.text.toString()}"
    }

    companion object {
        private const val ADD_ITEM_CONTROLLER = "addItemController"
    }

}