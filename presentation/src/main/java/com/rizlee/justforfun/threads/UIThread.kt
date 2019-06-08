package com.rizlee.justforfun.threads

import com.rizlee.domain.executor.PostExecutionThread
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UIThread @Inject constructor() : PostExecutionThread {

    override fun getScheduler() = AndroidSchedulers.mainThread()!!

}