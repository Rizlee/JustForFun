package com.rizlee.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun getScheduler(): Scheduler

}