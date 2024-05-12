package com.example.todolistfinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *   Created by Siddharth Singh Baghel as a Task assigned by Spider G
 **/
@Database(entities = [ToDoListDataEntity::class], version = 1)
abstract class ToDoListDatabase : RoomDatabase() {

    abstract fun toDoListDao(): ToDoListDAO

    companion object {
        @Volatile
        private var instance: ToDoListDatabase? = null

        fun getInstance(context: Context): ToDoListDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ToDoListDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ToDoListDatabase::class.java,
                "todolistdb"
            )
                .fallbackToDestructiveMigration() // This line ensures that Room handles migrations
                .build()
        }
    }

    // Adding a suspend function to execute database operations off the main thread
    suspend fun <T> executeDatabaseOperation(databaseOperation: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            databaseOperation.invoke()
        }
    }
}
