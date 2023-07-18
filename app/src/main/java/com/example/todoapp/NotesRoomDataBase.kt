package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesRoomDataBase : RoomDatabase() {

    abstract fun notesDao() : NotesDAO

    companion object {

        @Volatile
        private var INSTANCE: NotesRoomDataBase? = null

        fun getInstance(context: Context) : NotesRoomDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, NotesRoomDataBase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}