package com.rizlee.domain.interactors.base

import com.rizlee.domain.usecases.base.SingleUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.ResourceSubscriber

abstract class BaseInteractor {

    private var disposables: CompositeDisposable = CompositeDisposable()

    protected fun <InParam, OutParam> execute(
            useCase: SingleUseCase<InParam, OutParam>, inParam: InParam,
            observer: DisposableSingleObserver<OutParam>
    ) {
        disposables.add(useCase.execute(inParam).subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

}