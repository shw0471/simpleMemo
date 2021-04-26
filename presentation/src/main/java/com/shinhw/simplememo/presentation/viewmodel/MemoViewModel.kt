package com.shinhw.simplememo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.shinhw.domain.base.Result
import com.shinhw.domain.entity.Memo
import com.shinhw.domain.usecase.AddMemoUseCase
import com.shinhw.domain.usecase.DeleteMemoUseCase
import com.shinhw.domain.usecase.EditMemoUseCase
import com.shinhw.domain.usecase.GetMemoUseCase
import com.shinhw.simplememo.presentation.base.BaseViewModel
import com.shinhw.simplememo.presentation.base.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MemoViewModel(
    private val getMemoUseCase: GetMemoUseCase,
    private val addMemoUseCase: AddMemoUseCase,
    private val editMemoUseCase: EditMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase
) : BaseViewModel() {

    val messageEvent = SingleLiveEvent<String>()

    val memoList = MutableLiveData<List<Memo>?>()

    fun getMemoList() {
        val getResult = getMemoUseCase.create(Unit)

        val disposableSingleObserver = object : DisposableSingleObserver<Result<List<Memo>>>() {

            override fun onSuccess(t: Result<List<Memo>>) {
                if (t is Result.Success) memoList.value = t.data
            }

            override fun onError(e: Throwable) {
                messageEvent.setValue("Failed to get memo")
            }
        }

        val observer = getResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(disposableSingleObserver)

        addDisposable(observer)
    }

    fun addMemo(memo: Memo) {
        val addResult = addMemoUseCase.create(memo)

        val disposableSingleObserver = object : DisposableSingleObserver<Result<Unit>>() {

            override fun onSuccess(t: Result<Unit>) {
                messageEvent.setValue("Successfully added")
            }

            override fun onError(e: Throwable) {
                messageEvent.setValue("Failed to add")
            }
        }

        val observer = addResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(disposableSingleObserver)

        addDisposable(observer)
    }

    fun editMemo(memo: Memo) {
        val editResult = editMemoUseCase.create(memo)

        val disposableSingleObserver = object : DisposableSingleObserver<Result<Unit>>() {

            override fun onSuccess(t: Result<Unit>) {
                messageEvent.setValue("Successfully edited")
            }

            override fun onError(e: Throwable) {
                messageEvent.setValue("Failed to edit")
            }
        }

        val observer = editResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(disposableSingleObserver)

        addDisposable(observer)
    }

    fun deleteMemo(memo: Memo) {
        val deleteResult = deleteMemoUseCase.create(memo)

        val disposableSingleObserver = object : DisposableSingleObserver<Result<Unit>>() {

            override fun onSuccess(t: Result<Unit>) {
                messageEvent.setValue("Successfully deleted")
            }

            override fun onError(e: Throwable) {
                messageEvent.setValue("Failed to delete")
            }
        }

        val observer = deleteResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(disposableSingleObserver)

        addDisposable(observer)
    }
}