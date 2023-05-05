package br.com.exp.einkaufen.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.exp.einkaufen.data.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM data_items")
    fun getAll(): LiveData<List<Item>>

//    @Query("SELECT * FROM produtos_db WHERE item = (:item) ")
//    fun findByName(item: Item): Item

    @Insert
    fun insert(item: Item)

//    @Delete
//    fun delete(item: Item)
}