package com.example.todoapp

import java.util.Random

class DataBase private constructor(){
    private var dataBase: ArrayList<Note> = ArrayList()

    init {
        generateNotes()
    }

    fun addNoteToDataBase(note: Note) {
        dataBase.add(note)
    }



    fun getNotes(): ArrayList<Note> {
        return ArrayList(dataBase)
    }
    fun removeNote(id: Int) {
        val iterator = dataBase.iterator()
        while (iterator.hasNext()) {
            val note = iterator.next()
            if (note.uid == id) {
                iterator.remove()
            }
        }
    }


    private fun generateNotes(){
        val random = Random()
        for (i in 0..20) {
            val note = Note(i, "Note$i", random.nextInt(3))
            dataBase.add(note)
        }
    }

    companion object {
        private var instance: DataBase? = null
        fun getInstance(): DataBase?{
            if(instance == null){
                instance = DataBase()
            }
            return instance
        }
    }

}