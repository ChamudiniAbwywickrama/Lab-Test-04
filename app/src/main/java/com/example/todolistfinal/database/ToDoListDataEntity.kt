package com.example.todolistfinal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolist")
data class ToDoListDataEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "date") var date: String? = null,
    @ColumnInfo(name = "time") var time: String? = null,
    @ColumnInfo(name = "isShow") var isShow: Int = 0
) {
    // Custom setter for isShow field
    fun setIsShow(value: Int) {
        isShow = value
    }
}
