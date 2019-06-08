package com.rizlee.domain.usecases

import com.rizlee.domain.executor.ExecutionThread
import com.rizlee.domain.executor.PostExecutionThread
import com.rizlee.domain.repository.IRepository
import com.rizlee.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAnswerUseCase @Inject constructor(
        override val repository: IRepository,
        override val executionThread: ExecutionThread,
        override val postExecutionThread: PostExecutionThread
) : SingleUseCase<String, String>(repository, executionThread, postExecutionThread) {

    override fun buildUseCase(param: String): Single<String> {
        return repository.getAnswer(param)
    }
}