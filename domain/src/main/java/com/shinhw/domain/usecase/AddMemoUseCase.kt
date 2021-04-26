package com.shinhw.domain.usecase

import com.shinhw.domain.base.UseCase
import com.shinhw.domain.entity.Memo
import com.shinhw.domain.base.Result
import com.shinhw.domain.service.MemoService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class AddMemoUseCase(
    private val service: MemoService
) : UseCase<Memo, Result<Unit>>() {

    override fun create(data: Memo): Single<Result<Unit>> =
        service.addMemo(data)

}