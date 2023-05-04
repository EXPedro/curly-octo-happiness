package br.com.exp.einkaufen

import android.app.Application
import br.com.exp.einkaufen.data.AppDatabase
import br.com.exp.einkaufen.data.ItemRepository

class App: Application() {
    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy {ItemRepository(database.itemDao())}
}