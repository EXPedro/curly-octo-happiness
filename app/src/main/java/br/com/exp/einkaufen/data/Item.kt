package br.com.exp.einkaufen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "item") val item: String?
)