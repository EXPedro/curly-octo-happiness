package br.com.exp.einkaufen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_items")
data class Item(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @PrimaryKey val item: String
){
    override fun toString(): String {
        return item
    }
}