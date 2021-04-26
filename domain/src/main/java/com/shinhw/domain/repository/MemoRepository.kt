package com.shinhw.domain.repository

import com.shinhw.domain.entity.Memo
import io.reactivex.Completable
import io.reactivex.Single

interface MemoRepository {

    fun getMemoList(): Single<List<Memo>>

    fun addMemo(memo: Memo): Completable

    fun editMemo(memo: Memo): Completable

    fun deleteMemo(memo: Memo): Completable
}