package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.NoteItemBinding

class NotesAdapter(var onNoteClickListener: OnNoteClickListener) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
     private lateinit var notes: List<Note>

    fun setNotes(data: List<Note>){
        notes = data
        notifyDataSetChanged()
    }
    fun getNotes(): List<Note>{
        return ArrayList(notes)
    }

    inner class NotesViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        val tv = holder.binding.textViewNote
        tv.text = note.text
        val colorResId = when(note.priority){
            0 -> R.color.green
            1 -> R.color.orange
            else -> R.color.red
        }
        val color = ContextCompat.getColor(holder.itemView.context,colorResId)
        tv.setBackgroundColor(color)
        holder.itemView.setOnClickListener{
            onNoteClickListener.onClickNote(note)
        }
    }
    interface OnNoteClickListener{
        fun onClickNote(note:Note)
    }

}