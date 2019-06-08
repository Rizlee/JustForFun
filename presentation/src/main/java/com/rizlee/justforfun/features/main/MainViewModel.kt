package com.rizlee.justforfun.features.main

import androidx.lifecycle.MutableLiveData
import com.rizlee.domain.interactors.AIInteractor
import com.rizlee.justforfun.R
import com.rizlee.justforfun.di.Injector
import com.rizlee.justforfun.features.base.BaseViewModel
import com.rizlee.justforfun.features.main.adapter.ChatAdapter
import com.rizlee.justforfun.features.utils.lifecycle.Event
import io.reactivex.observers.DisposableSingleObserver
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(private val aiInteractor: AIInteractor) : BaseViewModel() {

    val scrollChatEvent = MutableLiveData<Event<Int>>()

    val adapter by lazy { ChatAdapter() }

    override fun dispose() = aiInteractor.dispose()

    override fun clearComponent() = Injector.clearMainComponent()

    override fun onViewCreated() = Unit

    private fun getAnswer(question: String) {
        aiInteractor.getAnswer(question, object : DisposableSingleObserver<String>() {
            override fun onSuccess(answer: String) =
                adapter.addMessage(ChatAdapter.Message(answer, true)).also {
                    scrollChatEvent.value = Event(adapter.itemCount)
                }

            override fun onError(exception: Throwable) {
                adapter.addMessage(ChatAdapter.Message(
                    if (exception.message == context.getString(com.rizlee.data.R.string.answer_4_2_unknown_location))
                        exception.message!!
                    else context.getString(R.string.answer_internet_problem), true
                ).also { scrollChatEvent.value = Event(adapter.itemCount) })
            }
        })
    }

    @Throws(IOException::class)
    internal fun sendMessage(message: String) {
        adapter.addMessage(ChatAdapter.Message(message, false))
            .also { scrollChatEvent.value = Event(adapter.itemCount) }
        getAnswer(message)
    }
}