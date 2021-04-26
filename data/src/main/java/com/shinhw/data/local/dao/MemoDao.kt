package com.shinhw.data.local.dao

import androidx.room.*
import com.shinhw.data.local.entity.MemoRoomEntity
import io.reactivex.Completable

@Dao
interface MemoDao {

    @Query("SELECT * FROM MemoRoomEntity")
    fun getMemoList(): List<MemoRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMemo(memo: MemoRoomEntity): Completable

    @Update
    fun editMemo(memo: MemoRoomEntity): Completable

    @Delete
    fun deleteMemo(memo: MemoRoomEntity): Completable
}