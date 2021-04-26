package com.shinhw.domain.usecase

import com.shinhw.domain.base.UseCase
import com.shinhw.domain.entity.Memo
import com.shinhw.domain.base.Result
import com.shinhw.domain.service.MemoService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetMemoUseCase(
    private val service: MemoService
) : UseCase<Unit, Result<List<Memo>>>() {

    override fun create(data: Unit): Single<Result<List<Memo>>> =
        service.getMemoList()

}