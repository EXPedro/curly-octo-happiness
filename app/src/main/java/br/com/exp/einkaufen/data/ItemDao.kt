package br.com.exp.einkaufen.data

import androidx.room.*
import br.com.exp.einkaufen.model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM data_items")
    fun getAll(): List<Item>

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)
}