package com.example.todoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val database = DataBase.getInstance()!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val text = binding.enterNote.text.toString().trimEnd()
        val priority = getPriority()
        val id = database.getNotes().size
        val note = Note(text = text, priority = priority, uid = id)
        database.addNoteToDataBase(note)
        finish()
    }

    private fun getPriority(): Int {
        val level = if (binding.lowBtn.isChecked) {
            0
        } else if (binding.mediumBtn.isChecked) 1
        else {
            2
        }
        return level
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity2::class.java)
        }
    }


}