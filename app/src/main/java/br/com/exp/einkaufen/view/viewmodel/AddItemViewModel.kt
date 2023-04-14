package br.com.exp.einkaufen.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class AddItemViewModel: ViewModel() {

    init {
        Log.i(ADD_ITEM_VIEW_MODEL, "ViewModel criado!" )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(ADD_ITEM_VIEW_MODEL, "AddItemViewModel destruído!")
    }

    companion object {
        private const val ADD_ITEM_VIEW_MODEL = "AddItemViewModel"
    }
}