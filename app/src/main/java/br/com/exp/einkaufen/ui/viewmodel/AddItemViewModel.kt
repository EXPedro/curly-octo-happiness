package br.com.exp.einkaufen.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.exp.einkaufen.data.AppDatabase
import br.com.exp.einkaufen.data.ItemRepository
import br.com.exp.einkaufen.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemViewModel(application: Application): ViewModel() {
    private val repository: ItemRepository
    private var readAll: List<Item>

    private var _newItems = MutableLiveData<String>()
    val newItems: LiveData<String>
        get() = _newItems

    init {
        val db = AppDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(db)
        readAll = repository.getAll()
    }

    fun setInputText (texto: MutableLiveData<String>){
        _newItems = texto
    }

    fun addItem(item: Item){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(item)
        }
    }

    fun getAll(): List<Item> {
        return readAll
    }

    fun updateItem(item: Item){
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(item)
            repository.update(item)
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(item)
        }
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

