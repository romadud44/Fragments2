package com.example.fragments2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val notes: MutableList<Note>) :
    RecyclerView.Adapter<CustomAdapter.NoteViewHolder>(){

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val numberTV: TextView = itemView.findViewById(R.id.numberTV)
        val textTV: TextView = itemView.findViewById(R.id.textTV)
        val dateTV: TextView = itemView.findViewById(R.id.dateTV)
        val checkTV: CheckBox = itemView.findViewById(R.id.checkCB)
    }
    private var onNoteClickListener: OnNoteClickListener? = null
    interface OnNoteClickListener {
        fun onNoteClick(note: Note, position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.numberTV.text = note.id.toString()
        holder.textTV.text = note.text
        holder.dateTV.text = note.date
        if (note.check){
            holder.checkTV.isChecked
        }
        holder.itemView.setOnClickListener {
            if (onNoteClickListener != null) {
                onNoteClickListener?.onNoteClick(note, position)
            }
        }

    }

    override fun getItemCount(): Int = notes.size


    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener){
        this.onNoteClickListener = onNoteClickListener
    }

}