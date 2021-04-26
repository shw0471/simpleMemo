package com.shinhw.domain.service

import com.shinhw.domain.entity.Memo
import com.shinhw.domain.base.Result
import io.reactivex.Single

interface MemoService {
    fun getMemoList(): Single<Result<List<Memo>>>

    fun addMemo(memo: Memo): Single<Result<Unit>>

    fun editMemo(memo: Memo): Single<Result<Unit>>

    fun deleteMemo(memo: Memo): Single<Result<Unit>>
}