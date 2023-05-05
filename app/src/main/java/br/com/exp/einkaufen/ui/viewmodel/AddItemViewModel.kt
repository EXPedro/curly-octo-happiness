package br.com.exp.einkaufen.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.RoomDatabase
import br.com.exp.einkaufen.data.AppDatabase
import br.com.exp.einkaufen.data.Item
import br.com.exp.einkaufen.data.ItemRepository

class AddItemViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ItemRepository = TODO()
    private var readAll: LiveData<List<Item>>
    private var _newItems = MutableLiveData<String>()
    val newItems: LiveData<String>
        get() = _newItems

    init {
        val db = AppDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(db)
//        readAll = (repository.getAll())
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