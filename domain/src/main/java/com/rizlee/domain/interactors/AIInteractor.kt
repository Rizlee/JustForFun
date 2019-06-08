package com.rizlee.domain.interactors

import com.rizlee.domain.interactors.base.BaseInteractor
import com.rizlee.domain.usecases.GetAnswerUseCase
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class AIInteractor @Inject constructor(private val getAnswerUseCase: GetAnswerUseCase) : BaseInteractor() {

    fun getAnswer(question: String, observer: DisposableSingleObserver<String>) = execute(getAnswerUseCase, question, observer)

}