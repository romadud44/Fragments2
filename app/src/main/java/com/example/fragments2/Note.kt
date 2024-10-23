package com.example.fragments2

import android.os.Parcel
import android.os.Parcelable

class Note(
    val id: Int,
    val text: String,
    val date: String,
    val check: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    ) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(text)
        parcel.writeString(date)
        parcel.writeByte(if (check) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        val notes: MutableList<Note> = mutableListOf()
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }

    }
}