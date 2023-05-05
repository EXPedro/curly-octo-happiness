package br.com.exp.einkaufen.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ItemRepository(private val dao: ItemDao) {

    fun insert(item: Item) = runBlocking {
        launch(Dispatchers.IO){
            dao.insert(item)
        }
    }

    fun update(item: Item) = runBlocking {
        launch(Dispatchers.IO){
            dao.update(item)
        }
    }

    fun delete(item: Item) = runBlocking {
        launch(Dispatchers.IO){
            dao.delete(item)
        }
    }

    fun getAll(): List<Item> = dao.getAll()
}