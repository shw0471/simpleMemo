package com.shinhw.data.repository

import com.shinhw.data.datasource.MemoDataSource
import com.shinhw.data.mapper.MemoEntityMapper
import com.shinhw.domain.entity.Memo
import com.shinhw.domain.repository.MemoRepository
import io.reactivex.Completable
import io.reactivex.Single

class MemoRepositoryImpl(
    private val dataSource: MemoDataSource,
    private val memoEntityMapper: MemoEntityMapper
) : MemoRepository {

    override fun getMemoList(): Single<List<Memo>> =
        Single.just(dataSource.getMemoList().map(memoEntityMapper::mapFrom))

    override fun addMemo(memo: Memo): Completable =
        dataSource.addMemo(memoEntityMapper.entityToRoom(memo))

    override fun editMemo(memo: Memo): Completable =
        dataSource.editMemo(memoEntityMapper.entityToRoom(memo))

    override fun deleteMemo(memo: Memo): Completable =
        dataSource.deleteMemo(memoEntityMapper.entityToRoom(memo))
}