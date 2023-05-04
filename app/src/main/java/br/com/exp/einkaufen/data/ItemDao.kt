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

    @Query("SELECT * FROM item WHERE id = (:id)")
    fun loadAllByIds(userIds: IntArray): List<Item>

    @Query("SELECT * FROM item WHERE item = (:item) ")
    fun findByName(first: String, last: String): Item

    @Insert
    fun insertAll(vararg users: Item)

    @Delete
    fun delete(user: Item)
}