package com.shinhw.domain.service

import com.shinhw.domain.entity.Memo
import com.shinhw.domain.base.Result
import com.shinhw.domain.repository.MemoRepository
import com.shinhw.domain.toResult
import com.shinhw.domain.toSingleResult
import io.reactivex.Single

class MemoServiceImpl(
    private val repository: MemoRepository
) : MemoService {

    override fun getMemoList(): Single<Result<List<Memo>>> =
        repository.getMemoList().toResult()

    override fun addMemo(memo: Memo): Single<Result<Unit>> =
        repository.addMemo(memo).toSingleResult()

    override fun editMemo(memo: Memo): Single<Result<Unit>> =
        repository.editMemo(memo).toSingleResult()

    override fun deleteMemo(memo: Memo): Single<Result<Unit>> =
        repository.deleteMemo(memo).toSingleResult()

}