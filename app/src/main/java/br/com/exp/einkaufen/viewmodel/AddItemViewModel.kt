package br.com.exp.einkaufen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddItemViewModel: ViewModel() {
    private var _newItems = MutableLiveData<String>()
    val newItems: LiveData<String>
        get() = _newItems

    init {
        Log.i(ADD_ITEM_VIEW_MODEL, "ViewModel criado!" )
    }

    fun setInputText (texto: MutableLiveData<String>){
        _newItems = texto
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(ADD_ITEM_VIEW_MODEL, "AddItemViewModel destruído!")
    }

    companion object {
        private const val ADD_ITEM_VIEW_MODEL = "AddItemViewModel"
    }
}