package com.rizlee.domain.repository

import io.reactivex.Single

interface IRepository {

    fun getAnswer(question: String): Single<String>

}