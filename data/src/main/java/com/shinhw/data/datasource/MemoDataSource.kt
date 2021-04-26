package com.shinhw.data.datasource

import com.shinhw.data.local.entity.MemoRoomEntity
import io.reactivex.Completable

interface MemoDataSource {

    fun getMemoList(): List<MemoRoomEntity>

    fun addMemo(memo: MemoRoomEntity): Completable

    fun editMemo(memo: MemoRoomEntity): Completable

    fun deleteMemo(memo: MemoRoomEntity): Completable
}