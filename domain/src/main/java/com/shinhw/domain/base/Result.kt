package com.shinhw.domain.base

sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(var data: T? = null) : Result<T>()
}
