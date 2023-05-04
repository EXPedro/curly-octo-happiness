package br.com.exp.einkaufen.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.exp.einkaufen.data.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Query("SELECT * FROM item WHERE item = (:item) ")
    fun findByName(item: Item): Item

    @Insert
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)
}