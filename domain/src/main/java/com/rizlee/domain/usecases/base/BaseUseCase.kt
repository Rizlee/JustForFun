package com.rizlee.domain.usecases.base

import com.rizlee.domain.executor.ExecutionThread
import com.rizlee.domain.executor.PostExecutionThread
import com.rizlee.domain.repository.IRepository

abstract class BaseUseCase(protected open val repository: IRepository,
                           protected open val executionThread: ExecutionThread,
                           protected open val postExecutionThread: PostExecutionThread
)