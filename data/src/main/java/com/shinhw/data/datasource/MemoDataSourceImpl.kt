package com.shinhw.data.datasource

import com.shinhw.data.local.dao.MemoDao
import com.shinhw.data.local.entity.MemoRoomEntity
import io.reactivex.Completable

class MemoDataSourceImpl(
    private val memoDao: MemoDao
) : MemoDataSource {

    override fun getMemoList(): List<MemoRoomEntity> =
        memoDao.getMemoList()

    override fun addMemo(memo: MemoRoomEntity): Completable =
        memoDao.addMemo(memo)

    override fun editMemo(memo: MemoRoomEntity): Completable =
        memoDao.editMemo(memo)

    override fun deleteMemo(memo: MemoRoomEntity): Completable =
        memoDao.deleteMemo(memo)
}