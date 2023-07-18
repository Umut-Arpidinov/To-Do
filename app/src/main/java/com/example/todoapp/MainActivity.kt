package com.example.todoapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NotesAdapter.OnNoteClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var notesRoomDataBase: NotesRoomDataBase


    private lateinit var notesAdapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notesAdapter = NotesAdapter(this)
        binding.rvNotes.adapter = notesAdapter

        initDb(applicationContext)


        setOnSwipeListener(binding.rvNotes)
        binding.addNoteBtn.setOnClickListener {
            MainActivity2.newIntent(this)
        }

    }

    private fun initDb(context: Context){
        notesRoomDataBase = NotesRoomDataBase.getInstance(context)
    }

    private fun setOnSwipeListener(rvShopList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = notesAdapter.getNotes()[position]
                notesRoomDataBase.notesDao().removeNote(note.uid)
                showNotes()
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    override fun onResume() {
        super.onResume()
        showNotes()
    }

    private fun showNotes() {
        notesAdapter.setNotes(notesRoomDataBase.notesDao().getNotes())
    }

    override fun onClickNote(note: Note) {
        notesRoomDataBase.notesDao().removeNote(note.uid)
        showNotes()
    }

}