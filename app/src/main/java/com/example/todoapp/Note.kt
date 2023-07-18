package com.example.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "priority") val priority: Int?
)