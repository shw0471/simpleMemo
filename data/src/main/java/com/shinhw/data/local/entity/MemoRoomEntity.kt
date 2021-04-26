package com.shinhw.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemoRoomEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,

    val memo: String,

    val date: String
)