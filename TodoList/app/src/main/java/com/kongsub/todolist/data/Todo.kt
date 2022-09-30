package com.kongsub.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Todo (
    val title: String,
    val date: Long = Calendar.getInstance().timeInMillis,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}