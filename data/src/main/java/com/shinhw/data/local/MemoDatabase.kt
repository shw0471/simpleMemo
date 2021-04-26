package com.shinhw.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shinhw.data.local.dao.MemoDao
import com.shinhw.data.local.entity.MemoRoomEntity

@Database(
    entities = [MemoRoomEntity::class],
    version = 1
)
abstract class MemoDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao
}