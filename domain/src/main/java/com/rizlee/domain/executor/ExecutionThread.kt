package com.rizlee.domain.executor

import io.reactivex.Scheduler

interface ExecutionThread {

    fun getScheduler(): Scheduler

}