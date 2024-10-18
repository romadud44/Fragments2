package com.example.fragments2

class Note(
    val id: Int,
    val text: String,
    val date: String,
    val check: Boolean) {
    companion object{
        val notes: MutableList<Note> = mutableListOf()
    }
}