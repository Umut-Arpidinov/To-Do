package com.example.todoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getNotes(): List<Note>

    @Insert
    fun addNote(note: Note)

    @Query("DELETE FROM notes WHERE uid = :id")
    fun removeNote(id: Int)
}