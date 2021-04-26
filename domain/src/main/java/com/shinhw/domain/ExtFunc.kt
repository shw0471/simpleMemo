package com.shinhw.domain

import io.reactivex.Single
import com.shinhw.domain.base.Result
import io.reactivex.Completable

fun <T> Single<T>.toResult(): Single<Result<T>> = this
    .map {
        Result.Success(it) as Result<T>
    }
    .onErrorReturn {
        Result.Error()
    }

fun Completable.toSingleResult(): Single<Result<Unit>> = this
    .toSingle {}.toResult()