package br.com.exp.einkaufen.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.RoomDatabase
import br.com.exp.einkaufen.data.AppDatabase
import br.com.exp.einkaufen.data.Item
import br.com.exp.einkaufen.data.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemViewModel(application: Application): ViewModel() {
    private val repository: ItemRepository
    private var readAll: LiveData<List<Item>>

    private var _newItems = MutableLiveData<String>()
    val newItems: LiveData<String>
        get() = _newItems

    init {
        val db = AppDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(db)
        readAll = repository.getAll()
        Log.i(ADD_ITEM_VIEW_MODEL, "ViewModel criado!" )
    }

    fun setInputText (texto: MutableLiveData<String>){
        _newItems = texto
    }

    fun addItem(item: Item){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(item)
        }
    }

    fun getAll(){
        readAll
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(ADD_ITEM_VIEW_MODEL, "AddItemViewModel destruído!")
    }

    companion object {
        private const val ADD_ITEM_VIEW_MODEL = "AddItemViewModel"
    }
}

class AddItemViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddItemViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

