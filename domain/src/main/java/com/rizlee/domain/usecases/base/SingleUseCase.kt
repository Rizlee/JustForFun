package com.rizlee.domain.usecases.base

import com.rizlee.domain.executor.ExecutionThread
import com.rizlee.domain.executor.PostExecutionThread
import com.rizlee.domain.repository.IRepository
import io.reactivex.Single

abstract class SingleUseCase<in T, out E>(
        override val repository: IRepository,
        override val executionThread: ExecutionThread,
        override val postExecutionThread: PostExecutionThread
) : BaseUseCase(repository, executionThread, postExecutionThread) {

    protected abstract fun buildUseCase(param: T): Single<out E>

    fun execute(param: T): Single<out E> {
        return buildUseCase(param)
                .subscribeOn(executionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
    }
}