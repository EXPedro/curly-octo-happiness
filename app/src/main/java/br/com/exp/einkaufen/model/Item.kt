package br.com.exp.einkaufen.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_items")
data class Item(
    @PrimaryKey val item: String
){
    override fun toString(): String {
        return item
    }
}