package br.com.exp.einkaufen.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.exp.einkaufen.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "produtos_db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
//                instance
                return instance
            }
        }
    }
}